package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByPersonAndPlaceAndEnd(Person person, Place place, LocalDateTime end);

    List<Presence> findAllByPersonId(long personId);
}
