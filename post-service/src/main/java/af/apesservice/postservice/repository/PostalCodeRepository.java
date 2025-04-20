package af.apesservice.postservice.repository;

import af.apesservice.postservice.model.entity.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {
    Optional<PostalCode> findByPostalCode(String postalCode);
    //  use the correct naming convention for Spring Data JPA query methods
    Boolean existsByPostalCode(String postalCode);
}
