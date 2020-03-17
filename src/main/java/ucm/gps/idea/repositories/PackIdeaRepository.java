package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.PackIdea;

@Repository
public interface PackIdeaRepository extends JpaRepository<PackIdea,Integer> {
}
