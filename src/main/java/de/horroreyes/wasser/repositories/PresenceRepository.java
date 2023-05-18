package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByPersonAndEnd(Person person, LocalDateTime end);

    List<Presence> findAllByPersonId(long personId);

    List<Presence> findByPersonAndPlaceAndEnd(Person person, Place place, LocalDateTime end);

    Optional<Presence> findActivePresenceByStartIsNotNullAndEndIsNullAndPersonId(long personId);

    List<Presence> findByEndIsNull();

    @Query("SELECT e FROM Presence e WHERE e.start > :start AND (e.end < :end OR e.end IS NULL)")
    List<Presence> findAllByStartAfterAndEndBeforeOrEndIsNull(LocalDateTime start, LocalDateTime end);
}
