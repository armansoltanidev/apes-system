package af.apesservice.postservice.repository;


import af.apesservice.postservice.model.baseModel.CommercialPostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialPostalCodeRepository extends JpaRepository<CommercialPostalCode, Long> {
}
