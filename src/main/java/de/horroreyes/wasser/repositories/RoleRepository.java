package de.horroreyes.wasser.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.horroreyes.wasser.model.Role;
import de.horroreyes.wasser.model.enums.Roles;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
