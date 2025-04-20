package af.apesservice.postservice.mapper;

import af.apesservice.postservice.model.entity.PostalCode;
import af.apesservice.postservice.model.dto.request.PostalCodeReqDto;
import af.apesservice.postservice.model.dto.response.PostalCodeResDto;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PostalCodeMapper {

    // Map from any PostalCode entity to response DTO
    PostalCodeResDto toDto(PostalCode postalCode);

    // Map from request DTO to UrbanPostalCode entity
    // Remove the zoneType mapping as it's now handled in the entity class
    PostalCode toEntity(PostalCodeReqDto postalCodeReqDto);
    
    // Map from request DTO to CommercialPostalCode entity
    // Remove the zoneType mapping as it's now handled in the entity class
}
