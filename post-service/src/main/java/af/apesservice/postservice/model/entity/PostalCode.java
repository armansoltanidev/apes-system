package af.apesservice.postservice.model.entity;

import af.apesservice.postservice.enums.PostalCodeStatus;
import af.apesservice.postservice.enums.VerifiedStatus;
import af.apesservice.postservice.enums.ZoneType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^\\d{10}$", message = "Postal code must be exactly 10 digits")
    private String postalCode;

    private String description;

    private Float latitude;
    private Float longitude;


    @NotNull
    private String avenue;


    @NotNull
    private String streetName;

    @NotNull
    private String alleyName;


    @NotNull
    private Integer houseNumber;

    @NotNull
    private Integer floorNumber;

    @NotNull
    private Integer unitNumber;

    private String buildingName;

    private String additionalInformation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ZoneType zoneType;


    @NotNull
    private PostalCodeStatus status = PostalCodeStatus.ACTIVE;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime deletedAt;

    private boolean isVerified;

    private VerifiedStatus verifiedStatus = VerifiedStatus.UNVERIFIED;

    private LocalDateTime VerifiedAt;


    @NotNull
    private String createdBy;



    // Add city relation
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}