package af.apesservice.postservice.model;

import af.apesservice.postservice.enums.PostalCodeStatus;
import af.apesservice.postservice.enums.VerifiedStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostalCodeReqDto {

    @NotNull(message = "postal code is required")
    private String postalCode;



    private String description;


    @NotNull
    @NotBlank
    @Size(max = 100, message = "Avenue name cannot exceed 100 characters")
    private String avenue;

    @NotNull(message = "Street name is required")
    @Size(max = 100, message = "Street name cannot exceed 100 characters")
    private String streetName;

    @NotNull(message = "Alley name is required")
    @Size(max = 100, message = "Alley name cannot exceed 100 characters")
    private String alleyName;

    @NotNull(message = "House number is required")
    private Integer houseNumber;

    @NotNull(message = "Floor number is required")
    private Integer floorNumber;

    @NotNull(message = "Unit number is required")
    private Integer unitNumber;

    private String buildingName;
    private String additionalInformation;



    @NotNull
    @Enumerated(EnumType.STRING)
    private PostalCodeStatus status = PostalCodeStatus.ACTIVE;


    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();


    @NotNull
    @Enumerated(EnumType.STRING)
    private VerifiedStatus verifiedStatus = VerifiedStatus.UNVERIFIED;


    @NotNull
    @NotBlank(message = "Created by can not be null")
    private String createdBy;
}
