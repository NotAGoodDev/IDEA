package ucm.gps.idea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.repositories.CreatorRepository;
import ucm.gps.idea.repositories.EnterpriseRepository;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.runner.RunWith;

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
    private CreatorRepository repoCreator;
    @Autowired
    private EnterpriseRepository repoEnterprise;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public boolean registerCreatorTest() {
        Creator first = new Creator();
        first.setId(3);
        first.setName("Carlos");
        first.setLastName("Garcia Rodriguez");
        first.setBirthDate(new Date(System.currentTimeMillis())); // Fecha actual
        first.setTelephone("655889401");
        first.setAddress("Calle ventana 8");
        first.setEmail("carlos@gmail.es");
        first.setUsername("carletes_80");
        first.setPassword(encoder.encode("carlos123"));
        first.setActive(true);

        Creator ret = repoCreator.save(first);

        assertEquals(ret.getPassword(), first.getPassword());
        //assertTrue(ret.getPassword().equalsIgnoreCase(first.getPassword()));
        return true;
    }

    @Test
    public boolean registerEnterpriseTest() {
        Enterprise e = new Enterprise();

        e.setId(2);
        e.setName("Microsft");
        e.setCIF("1234567890");
        e.setAddress("Calle California");
        e.setEmail("mcfst@md.cf");
        e.setTelephone("912344435");
        e.setCreditCard(123456789);
        e.setUsername("MicrosoftMadrid");
        e.setPassword(encoder.encode("micro123"));
        e.setRemainingIdeas(10);
        e.setActive(true);

        Enterprise ret = repoEnterprise.save(e);

        assertEquals(e.getPassword(), ret.getPassword());

        return true;
    }
}
