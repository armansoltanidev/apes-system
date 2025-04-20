package af.apesservice.postservice.model.dto.response;


import af.apesservice.postservice.enums.VerifiedStatus;
import af.apesservice.postservice.enums.ZoneType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostalCodeResDto {
    private Long id;
    private String postalCode;
    private String description;
    private String avenue;
    private String streetName;
    private String alleyName;
    private String houseNumber;
    private Integer floorNumber;
    private Integer unitNumber;
    private String buildingName;
    private String additionalInformation;
    private ZoneType zoneType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private VerifiedStatus verifiedStatus;
    private LocalDateTime verifiedAt;
}
