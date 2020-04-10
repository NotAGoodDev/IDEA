package ucm.gps.idea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ucm.gps.idea.repositories")
public class IdeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);
    }
}