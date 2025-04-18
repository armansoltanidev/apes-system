package af.apesservice.postservice.model;

import java.time.LocalDateTime;

import af.apesservice.postservice.enums.VerifiedStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VerifiedResponse {
    private String postalCode;
    private boolean verified;
    private VerifiedStatus verifiedStatus;
    private LocalDateTime requestedAt = LocalDateTime.now();


    public VerifiedResponse() {
    }

    public VerifiedResponse(String postalCode, boolean verified, VerifiedStatus verifiedStatus) {
        this.postalCode = postalCode;
        this.verified = verified;
        this.verifiedStatus = verifiedStatus;
    }
 
}
