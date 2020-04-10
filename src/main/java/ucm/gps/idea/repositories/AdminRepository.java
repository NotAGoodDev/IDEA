package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucm.gps.idea.entities.Admin;


@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer> {

}
