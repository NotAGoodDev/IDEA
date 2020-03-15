package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucm.gps.idea.entities.Deal;

public interface DealRepository extends JpaRepository<Deal,Integer> {
}

