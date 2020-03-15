package ucm.gps.idea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucm.gps.idea.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

}
