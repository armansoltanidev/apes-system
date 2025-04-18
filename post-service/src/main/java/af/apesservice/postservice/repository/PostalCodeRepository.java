package af.apesservice.postservice.repository;

import af.apesservice.postservice.model.baseModel.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {
}
