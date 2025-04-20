package af.apesservice.postservice.service;

import af.apesservice.postservice.exception.PostalCodeNotFoundException;
import af.apesservice.postservice.model.entity.PostalCode;
import af.apesservice.postservice.model.dto.request.PostalCodeReqDto;
import af.apesservice.postservice.model.dto.response.PostalCodeResDto;
import af.apesservice.postservice.model.dto.response.VerifiedResDto;
import af.apesservice.postservice.repository.PostalCodeRepository;
import af.apesservice.postservice.service.postalcode.PostalCodeGenerator;
import af.apesservice.postservice.service.postalcode.PostalCodeGeneratorFactory;
import af.apesservice.postservice.mapper.PostalCodeMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import af.apesservice.postservice.service.address.AddressFormatter;
import af.apesservice.postservice.service.address.AddressFormatterFactory;

@Service
public class PostalCodeService {

    private final PostalCodeRepository postalCodeRepository;
    private final PostalCodeMapper postalCodeMapper;
    private final PostalCodeGeneratorFactory postalCodeGeneratorFactory;
    private final AddressFormatterFactory addressFormatterFactory;

    public PostalCodeService(PostalCodeRepository postalCodeRepository,
            PostalCodeMapper postalCodeMapper,
            PostalCodeGeneratorFactory postalCodeGeneratorFactory,
            AddressFormatterFactory addressFormatterFactory) {
        this.postalCodeRepository = postalCodeRepository;
        this.postalCodeMapper = postalCodeMapper;
        this.postalCodeGeneratorFactory = postalCodeGeneratorFactory;
        this.addressFormatterFactory = addressFormatterFactory;
    }

    public String generatePostalCode(PostalCodeReqDto requestDto) {
        // Get the appropriate generator from the factory
        PostalCodeGenerator generator = postalCodeGeneratorFactory.getGenerator();

        // Generate the postal code
        return generator.generate(requestDto);
    }

    public List<PostalCodeResDto> findAllPostalCodes() {
        return postalCodeRepository.findAll().stream().map(postalCodeMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get formatted address based on postal code with optional coordinates
     * 
     * @param postalCode         The postal code to look up
     * @param includeCoordinates Whether to include latitude and longitude in the
     *                           response
     * @return Formatted address information
     */
    public Map<String, Object> getFormattedAddress(String postalCode, boolean includeCoordinates) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new PostalCodeNotFoundException("Postal code cannot be null or empty");
        }

        PostalCode foundPostalCode = postalCodeRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(postalCode));

        // Use the address formatter factory to get the appropriate formatter
        AddressFormatter formatter = addressFormatterFactory.getFormatter(foundPostalCode.getZoneType());

        // Generate the formatted address using the formatter
        return formatter.format(foundPostalCode, includeCoordinates);
    }

    public PostalCodeResDto createPostalCode(PostalCodeReqDto postalCodeReqDto) {
        // Generate postal code if not provided
        String generatedPostalCode = generatePostalCode(postalCodeReqDto);
        postalCodeReqDto.setPostalCode(generatedPostalCode);

        PostalCode saved = postalCodeMapper.toEntity(postalCodeReqDto);
        saved = postalCodeRepository.save(saved);
        return postalCodeMapper.toDto(saved);
    }

    public VerifiedResDto checkVerifiedStatus(String postalCode) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new PostalCodeNotFoundException("Postal code cannot be null or empty");
        }
        PostalCode founded = postalCodeRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(postalCode));

        return new VerifiedResDto(founded.getPostalCode(), founded.isVerified(), founded.getVerifiedStatus());
    }

    public Boolean checkPostalCodeExists(String postalCode) {
        return postalCodeRepository.existsByPostalCode(postalCode);
    }

}
