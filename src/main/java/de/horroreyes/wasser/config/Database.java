package de.horroreyes.wasser.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.model.Role;
import de.horroreyes.wasser.model.User;
import de.horroreyes.wasser.model.enums.Roles;
import de.horroreyes.wasser.model.enums.Status;
import de.horroreyes.wasser.repositories.PersonRepository;
import de.horroreyes.wasser.repositories.PlaceRepository;
import de.horroreyes.wasser.repositories.RoleRepository;
import de.horroreyes.wasser.repositories.UserRepository;
import lombok.extern.java.Log;

@Configuration
@Log
class Database {

    public static final String PRELOADING = "Preloading ";

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, PlaceRepository placeRepository,
            UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            if (placeRepository.count() == 0) {
                log.info(PRELOADING + placeRepository.save(new Place("Bultensee")));
            }
            if (roleRepository.count() == 0) {
                log.info(PRELOADING + roleRepository.save(new Role(Roles.ROLE_ADMIN)));
                log.info(PRELOADING + roleRepository.save(new Role(Roles.ROLE_MODERATOR)));
                log.info(PRELOADING + roleRepository.save(new Role(Roles.ROLE_USER)));
            }
            if (personRepository.count() == 0) {
                log.info(PRELOADING + personRepository.save(new Person("Bilbo", "Baggins", Status.ACTIVE)));
                log.info(PRELOADING + personRepository.save(new Person("Frodo", "Baggins", Status.ILL)));
            }
            if (userRepository.count() == 0) {
                User admin = User.builder().username("admin").email("admin@localhost.local")
                        .password(passwordEncoder.encode("admin")).roles(roleRepository.findAll()).build();
                log.info(PRELOADING + userRepository.save(admin));

                User user = User.builder().username("user").email("user@localhost.local")
                        .password(passwordEncoder.encode("user"))
                        .role(roleRepository.findByName(Roles.ROLE_USER).orElseThrow()).build();
                log.info(PRELOADING + userRepository.save(user));
            }
        };
    }
}
