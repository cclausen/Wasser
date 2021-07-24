package de.horroreyes.wasser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("de.horroreyes.wasser.repositories")
public class WasserApplication {

    public static void main(String[] args) {
        SpringApplication.run(WasserApplication.class, args);
    }

}
