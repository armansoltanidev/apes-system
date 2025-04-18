package af.apesservice.postservice.service;


import af.apesservice.postservice.model.resModel.PostalCodeResDto;
import af.apesservice.postservice.repository.PostalCodeRepository;
import af.apesservice.postservice.utils.PostalCodeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostalCodeService {


    private final PostalCodeRepository postalCodeRepository;
    private final PostalCodeMapper postalCodeMapper;


    public PostalCodeService(PostalCodeRepository postalCodeRepository, PostalCodeMapper postalCodeMapper) {
        this.postalCodeRepository = postalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
    }


    public List<PostalCodeResDto> findAllPostalCodes() {
        return postalCodeRepository.findAll().stream().map(postalCodeMapper::toDto).collect(Collectors.toList());
    }




}
