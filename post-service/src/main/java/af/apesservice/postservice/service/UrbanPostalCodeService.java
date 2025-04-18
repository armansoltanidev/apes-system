package af.apesservice.postservice.service;


import af.apesservice.postservice.exception.CityIdNotFoundExeption;
import af.apesservice.postservice.exception.PostalCodeNotFoundException;
import af.apesservice.postservice.model.baseModel.City;
import af.apesservice.postservice.model.reqModel.PostalCodeReqDto;
import af.apesservice.postservice.model.resModel.PostalCodeResDto;
import af.apesservice.postservice.model.baseModel.UrbanPostalCode;
import af.apesservice.postservice.model.resModel.VerifiedResDto;
import af.apesservice.postservice.repository.CityRepository;
import af.apesservice.postservice.repository.UrbanPostalCodeRepository;
import af.apesservice.postservice.utils.PostalCodeMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UrbanPostalCodeService {
    private final UrbanPostalCodeRepository urbanPostalCodeRepository;
    private final PostalCodeMapper postalCodeMapper;
    private final CityRepository cityRepository;

    public UrbanPostalCodeService(UrbanPostalCodeRepository urbanPostalCodeRepository, PostalCodeMapper postalCodeMapper, CityRepository cityRepository) {
        this.urbanPostalCodeRepository = urbanPostalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
        this.cityRepository = cityRepository;

    }


    @Cacheable("urbanPostalCodes")
    public List<PostalCodeResDto> findAllUrbanPostalCode() {
        log.info("find all URBAN postal code");
        return urbanPostalCodeRepository.findAll().stream()
                .map(postalCodeMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public PostalCodeResDto createPostalCode(PostalCodeReqDto postalCodeReqDto) {


        City city = cityRepository.findById(postalCodeReqDto.getCityId())
                .orElseThrow(() -> new CityIdNotFoundExeption("City not found with ID: " + postalCodeReqDto.getCityId()));

        // Convert request DTO to an entity using MapStruct
        UrbanPostalCode urbanPostalCode = postalCodeMapper.toEntity(postalCodeReqDto);

         urbanPostalCode.setCity(city);
         urbanPostalCode.setCityName(city.getName());


        // Save the entity to a database
        UrbanPostalCode savedPostalCode = urbanPostalCodeRepository.save(urbanPostalCode);

        // Convert entity to response DTO and return using MapStruct
        log.info("Created new Urban postal code with id: {}", savedPostalCode.getId());
        return postalCodeMapper.toDto(savedPostalCode);

    }


    public VerifiedResDto checkVerifiedStatus(String postalCode) {
        log.info("checkVerifiedStatus: called for postal code: id: {}", postalCode);
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }

        UrbanPostalCode urbanPostalCode = urbanPostalCodeRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(postalCode));

        return new VerifiedResDto(urbanPostalCode.getPostalCode(), urbanPostalCode.isVerified(), urbanPostalCode.getVerifiedStatus());
    }


}
