package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {
}

