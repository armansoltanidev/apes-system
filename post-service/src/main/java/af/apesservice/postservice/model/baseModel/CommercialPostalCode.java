package af.apesservice.postservice.model.baseModel;


import af.apesservice.postservice.enums.ZoneType;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue("COMMERCIAL")
public class CommercialPostalCode extends PostalCode {

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false, name = "zone_type")
    private ZoneType zoneType = ZoneType.COMMERCIAL;

}
