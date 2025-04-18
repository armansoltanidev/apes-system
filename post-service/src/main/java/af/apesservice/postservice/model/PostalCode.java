package af.apesservice.postservice.model;


import af.apesservice.postservice.enums.PostalCodeStatus;
import af.apesservice.postservice.enums.VerifiedStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String postalCode;

    private String description;

    private Float latitude;
    private Float longitude;

    private String avenue;

    private String streetName;

    private String alleyName;

    private String houseNumber;

    private Integer floorNumber;

    private Integer unitNumber;

    private String buildingName;


    private String additionalInformation;

    @Enumerated(EnumType.STRING)
    private PostalCodeStatus status = PostalCodeStatus.ACTIVE;


    

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime deletedAt;

    private boolean isVerified;


    @Enumerated(EnumType.STRING)
    private VerifiedStatus verifiedStatus = VerifiedStatus.UNVERIFIED;

    private LocalDateTime VerifiedAt;


    private String createdBy;


}
