package de.horroreyes.wasser.config;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Status;
import de.horroreyes.wasser.repositories.PersonRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
class Database {

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository) {

        return args -> {
            log.info("Preloading " + personRepository.save(new Person("Bilbo", "Baggins", Status.ACTIVE)));
            log.info("Preloading " + personRepository.save(new Person("Frodo", "Baggins", Status.ACTIVE)));
        };
    }
}
