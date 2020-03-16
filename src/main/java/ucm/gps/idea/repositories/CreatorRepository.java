package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Creator;

@Repository
public interface CreatorRepository extends JpaRepository<Creator,Integer> {
    public Creator findByUsername(String username);
}
