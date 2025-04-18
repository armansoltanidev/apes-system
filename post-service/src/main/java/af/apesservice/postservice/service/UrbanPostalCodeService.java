package af.apesservice.postservice.service;


import af.apesservice.postservice.exception.PostalCodeNotFoundException;
import af.apesservice.postservice.model.PostalCodeReqDto;
import af.apesservice.postservice.model.PostalCodeResDto;
import af.apesservice.postservice.model.UrbanPostalCode;
import af.apesservice.postservice.model.VerifiedResponse;
import af.apesservice.postservice.repository.UrbanPostalCodeRepository;
import af.apesservice.postservice.utils.PostalCodeMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrbanPostalCodeService {
    private final UrbanPostalCodeRepository urbanPostalCodeRepository;
    private final PostalCodeMapper postalCodeMapper;

    private final Logger logger = LoggerFactory.getLogger(CommercialPostalCodeService.class);


    public UrbanPostalCodeService(UrbanPostalCodeRepository urbanPostalCodeRepository,
                                  PostalCodeMapper postalCodeMapper) {
        this.urbanPostalCodeRepository = urbanPostalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
    }


    @Cacheable("urbanPostalCodes")
    public List<PostalCodeResDto> findAllUrbanPostalCode() {
        return urbanPostalCodeRepository.findAll().stream()
        .map(postalCodeMapper::toDto)
        .collect(Collectors.toList());
                
    }


    @Transactional
    public PostalCodeResDto createPostalCode(PostalCodeReqDto postalCodeReqDto) {

        // Convert request DTO to an entity using MapStruct
        UrbanPostalCode urbanPostalCode = postalCodeMapper.toEntity(postalCodeReqDto);

        // Save the entity to a database
        UrbanPostalCode savedPostalCode = urbanPostalCodeRepository.save(urbanPostalCode);

        // Convert entity to response DTO and return using MapStruct
        logger.info("Created new commercial postal code with id: {}", savedPostalCode.getId());
        return postalCodeMapper.toDto(savedPostalCode);

    }


    public VerifiedResponse checkVerifiedStatus(String postalCode) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }
        
        UrbanPostalCode urbanPostalCode = urbanPostalCodeRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(postalCode));
        
        return new VerifiedResponse(urbanPostalCode.getPostalCode(), urbanPostalCode.isVerified(), urbanPostalCode.getVerifiedStatus());
    }


}
