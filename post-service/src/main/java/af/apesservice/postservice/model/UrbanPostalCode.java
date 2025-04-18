package af.apesservice.postservice.model;


import af.apesservice.postservice.enums.ZoneType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue("URBAN")
public class UrbanPostalCode extends PostalCode {

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false, name = "zone_type")
    private ZoneType zoneType = ZoneType.RESIDENTIAL;

}
