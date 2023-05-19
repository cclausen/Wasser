package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Long> {
    Optional<Duty> findByDateIs(LocalDate date);
}
