package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Enterprise;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise,Integer> {
}