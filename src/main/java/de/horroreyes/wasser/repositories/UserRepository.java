package de.horroreyes.wasser.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.horroreyes.wasser.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
