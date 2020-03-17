package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Idea;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea,Integer> {

    public List<Idea> findByOrderByCreatedAtDesc();


}
