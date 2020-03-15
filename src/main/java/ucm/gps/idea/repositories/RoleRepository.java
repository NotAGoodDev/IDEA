package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucm.gps.idea.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
