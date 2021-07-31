package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
