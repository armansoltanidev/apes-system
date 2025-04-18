package af.apesservice.postservice.service;


import af.apesservice.postservice.model.baseModel.CommercialPostalCode;
import af.apesservice.postservice.model.reqModel.CommercialPostalCodeReqDto;
import af.apesservice.postservice.model.resModel.PostalCodeResDto;
import af.apesservice.postservice.repository.CommercialPostalCodeRepository;
import af.apesservice.postservice.utils.PostalCodeMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CommercialPostalCodeService  {
    private final CommercialPostalCodeRepository commercialPostalCodeRepository;

    private final PostalCodeMapper postalCodeMapper;

    public CommercialPostalCodeService(CommercialPostalCodeRepository commercialPostalCodeRepository, PostalCodeMapper postalCodeMapper) {
        this.commercialPostalCodeRepository = commercialPostalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
    }


    @Cacheable("commercialPostalCodes")
    public List<PostalCodeResDto> findAllCommercialPostalCodes() {
        log.info("Fetching all commercial postal codes");
        return commercialPostalCodeRepository.findAll().stream()
                .map(postalCodeMapper::toDto)
                .collect(Collectors.toList());
    }
    

    @Transactional
    public PostalCodeResDto createPostalCode(CommercialPostalCodeReqDto dto) {
        //TODO:
        //Validate Input

        CommercialPostalCode postalCode = postalCodeMapper.toEntity(dto);
        CommercialPostalCode saved = commercialPostalCodeRepository.save(postalCode);

        log.info("Created new commercial postal code with id: {}", saved.getId());
        return postalCodeMapper.toDto(saved);
    }
}