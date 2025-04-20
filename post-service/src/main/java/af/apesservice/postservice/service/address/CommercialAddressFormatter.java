package af.apesservice.postservice.service.address;

import af.apesservice.postservice.model.entity.PostalCode;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Commercial-specific address formatter
 */
@Component
public class CommercialAddressFormatter extends StandardAddressFormatter {
    
    private static final String PERSIAN_SEPARATOR = "، ";
    
    @Override
    protected String formatAddressString(PostalCode postalCode) {
        StringBuilder formattedAddress = new StringBuilder();
        
        // For commercial addresses, we might want to format differently
        formattedAddress.append("محل تجاری: ");
        
        // Add building name with prominence for commercial addresses
        if (StringUtils.hasText(postalCode.getBuildingName())) {
            formattedAddress.append(postalCode.getBuildingName()).append(PERSIAN_SEPARATOR);
        }
        
        // Add avenue and street
        appendIfPresent(formattedAddress, postalCode.getAvenue(), PERSIAN_SEPARATOR);
        appendIfPresent(formattedAddress, postalCode.getStreetName(), PERSIAN_SEPARATOR);
        
        // Add alley if present
        if (StringUtils.hasText(postalCode.getAlleyName())) {
            formattedAddress.append(postalCode.getAlleyName()).append(PERSIAN_SEPARATOR);
        }
        
        // Add house, floor, and unit numbers
        formattedAddress.append(" پلاک: ").append(postalCode.getHouseNumber()).append(PERSIAN_SEPARATOR);
        formattedAddress.append(" طبقه: ").append(postalCode.getFloorNumber()).append(PERSIAN_SEPARATOR);
        formattedAddress.append(" واحد تجاری: ").append(postalCode.getUnitNumber());
        
        // Add city name if available
        if (postalCode.getCity() != null && StringUtils.hasText(postalCode.getCity().getName())) {
            formattedAddress.append(PERSIAN_SEPARATOR).append(postalCode.getCity().getName());
        }
        
        return formattedAddress.toString();
    }
    
    @Override
    protected void addAdditionalFields(Map<String, Object> addressInfo, PostalCode postalCode) {
        addressInfo.put("addressType", "تجاری");
    }
}