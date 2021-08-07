package de.horroreyes.wasser.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.model.Status;
import de.horroreyes.wasser.model.User;
import de.horroreyes.wasser.repositories.PersonRepository;
import de.horroreyes.wasser.repositories.PlaceRepository;
import de.horroreyes.wasser.repositories.UserRepository;
import lombok.extern.java.Log;

@Configuration
@Log
class Database {

    public static final String PRELOADING = "Preloading ";

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, PlaceRepository placeRepository,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            if (placeRepository.count() == 0) {
                log.info(PRELOADING + placeRepository.save(new Place("Bultensee")));
            }
            if (personRepository.count() == 0) {
                log.info(PRELOADING + personRepository.save(new Person("Bilbo", "Baggins", Status.ACTIVE)));
                log.info(PRELOADING + personRepository.save(new Person("Frodo", "Baggins", Status.ILL)));
            }
            if (userRepository.count() == 0) {
                log.info(PRELOADING + userRepository.save(
                        new User("admin", "admin@localhost.local", passwordEncoder.encode("admin"))));
                log.info(PRELOADING + userRepository.save(
                        new User("user", "user@localhost.local", passwordEncoder.encode("user"))));
            }
        };
    }
}
