package af.apesservice.postservice.model;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PostalCodeVerifyRequest {
    private String postalCode;



    public PostalCodeVerifyRequest(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}