package af.apesservice.postservice.model.baseModel;

import af.apesservice.postservice.enums.PostalCodeStatus;
import af.apesservice.postservice.enums.VerifiedStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String postalCode;

    private String description;

    private Float latitude;
    private Float longitude;

    private String avenue;

    private String streetName;

    private String alleyName;

    private Integer houseNumber;

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

    private String cityName;

    // Add city relation
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}