package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
