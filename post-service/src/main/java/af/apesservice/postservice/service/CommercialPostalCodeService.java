package af.apesservice.postservice.service;


import af.apesservice.postservice.model.*;
import af.apesservice.postservice.repository.CommercialPostalCodeRepository;
import af.apesservice.postservice.utils.PostalCodeMapper;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommercialPostalCodeService  {
    private final Logger logger = LoggerFactory.getLogger(CommercialPostalCodeService.class);
    private final CommercialPostalCodeRepository commercialPostalCodeRepository;

    private final PostalCodeMapper postalCodeMapper;

    public CommercialPostalCodeService(CommercialPostalCodeRepository commercialPostalCodeRepository, PostalCodeMapper postalCodeMapper) {
        this.commercialPostalCodeRepository = commercialPostalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
    }


    @Cacheable("commercialPostalCodes")
    public List<PostalCodeResDto> findAllCommercialPostalCodes() {
        logger.info("Fetching all commercial postal codes");
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
        
        logger.info("Created new commercial postal code with id: {}", saved.getId());
        return postalCodeMapper.toDto(saved);
    }
}