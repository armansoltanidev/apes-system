package af.apesservice.postservice.repository;

import af.apesservice.postservice.model.baseModel.UrbanPostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UrbanPostalCodeRepository extends JpaRepository<UrbanPostalCode, Long> {

    Optional<UrbanPostalCode> findByPostalCode(String postalCode);
}
