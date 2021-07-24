package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
}
