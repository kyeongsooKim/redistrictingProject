package america.redistricting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "america.redistricting.repository")
@SpringBootApplication
public class RedistrictingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistrictingApplication.class, args);
    }
}
