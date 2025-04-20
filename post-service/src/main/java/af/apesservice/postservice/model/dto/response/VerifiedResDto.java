package af.apesservice.postservice.model.dto.response;

import java.time.LocalDateTime;

import af.apesservice.postservice.enums.VerifiedStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class VerifiedResDto {
    private String postalCode;
    private boolean verified;
    private VerifiedStatus verifiedStatus;
    private LocalDateTime requestedAt = LocalDateTime.now();


    public VerifiedResDto(String postalCode, boolean verified, VerifiedStatus verifiedStatus) {
        this.postalCode = postalCode;
        this.verified = verified;
        this.verifiedStatus = verifiedStatus;
        this.requestedAt = LocalDateTime.now();
    }

}
