package de.horroreyes.wasser.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Presence;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByPersonAndEnd(Person person, LocalDateTime end);

    List<Presence> findAllByPersonId(long personId);
}
