package af.apesservice.postservice.utils;

import af.apesservice.postservice.model.baseModel.PostalCode;
import af.apesservice.postservice.model.reqModel.PostalCodeReqDto;
import af.apesservice.postservice.model.resModel.PostalCodeResDto;
import af.apesservice.postservice.model.baseModel.UrbanPostalCode;
import af.apesservice.postservice.model.baseModel.CommercialPostalCode;
import af.apesservice.postservice.model.reqModel.CommercialPostalCodeReqDto;

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
    UrbanPostalCode toEntity(PostalCodeReqDto postalCodeReqDto);
    
    // Map from request DTO to CommercialPostalCode entity
    // Remove the zoneType mapping as it's now handled in the entity class
    CommercialPostalCode toEntity(CommercialPostalCodeReqDto commercialPostalCodeReqDto);
}
