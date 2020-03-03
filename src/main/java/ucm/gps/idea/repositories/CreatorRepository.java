package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucm.gps.idea.entities.Creator;

public interface CreatorRepository extends JpaRepository<Creator,Integer> {

}
