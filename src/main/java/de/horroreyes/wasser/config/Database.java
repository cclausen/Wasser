package de.horroreyes.wasser.config;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.model.Status;
import de.horroreyes.wasser.repositories.PersonRepository;
import de.horroreyes.wasser.repositories.PlaceRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
class Database {

    public static final String PRELOADING = "Preloading ";

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, PlaceRepository placeRepository) {

        return args -> {
            if (placeRepository.count() == 0) {
                log.info(PRELOADING + placeRepository.save(new Place("Bultensee")));
            }
            if (personRepository.count() == 0) {
                log.info(PRELOADING + personRepository.save(new Person("Bilbo", "Baggins", Status.ACTIVE)));
                log.info(PRELOADING + personRepository.save(new Person("Frodo", "Baggins", Status.ACTIVE)));
            }
        };
    }
}
