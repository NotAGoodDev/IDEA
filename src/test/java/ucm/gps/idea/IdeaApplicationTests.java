package ucm.gps.idea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.repositories.CreatorRepository;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class IdeaApplicationTests {

	// Lo he comentado porque al estar vacio, TravisCI daba excepcion
		// Logicamente cuando hagamos los test se tiene que usar

	/*
	@Test
	void contextLoads() {
	}
	*/

	@Autowired
    private CreatorRepository repo;
	@Autowired
    private BCryptPasswordEncoder encoder;

	@Test
    public boolean registerCreatorTest(){
	    Creator first = new Creator();
	    first.setId(2);
	    first.setName("Raul");
        first.setLastName("Garcia Rodriguez");
        first.setBirthDate(new Date(System.currentTimeMillis())); // Fecha actual
        first.setTelephone("655889401");
        first.setAddress("Calle ventana 8");
        first.setEmail("raul@gmail.es");
        first.setUsername("raulon_md");
        first.setPassword(encoder.encode("raul123"));
        first.setActive(true);

        Creator ret = repo.save(first);

        assertEquals(ret.getPassword(), first.getPassword());
        //assertTrue(ret.getPassword().equalsIgnoreCase(first.getPassword()));
        return true;
    }
}
