package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findByName(String name);
}
