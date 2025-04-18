package af.apesservice.postservice.utils;

import af.apesservice.postservice.model.PostalCode;
import af.apesservice.postservice.model.PostalCodeReqDto;
import af.apesservice.postservice.model.PostalCodeResDto;
import af.apesservice.postservice.model.UrbanPostalCode;
import af.apesservice.postservice.model.CommercialPostalCode;
import af.apesservice.postservice.model.CommercialPostalCodeReqDto;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    UrbanPostalCode toEntity(PostalCodeReqDto postalCodeReqDto);
    
    // Map from request DTO to CommercialPostalCode entity
    // Remove the zoneType mapping as it's now handled in the entity class
    CommercialPostalCode toEntity(CommercialPostalCodeReqDto commercialPostalCodeReqDto);
}
