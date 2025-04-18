package af.apesservice.postservice.exception;

public class PostalCodeNotFoundException extends RuntimeException {
    
    public PostalCodeNotFoundException(String postalCode) {
        super("Postal code not found: " + postalCode);
    }
}