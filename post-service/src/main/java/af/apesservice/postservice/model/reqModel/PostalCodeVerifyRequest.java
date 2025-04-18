package af.apesservice.postservice.model.reqModel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostalCodeVerifyRequest {
    private String postalCode;

}