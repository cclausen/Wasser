package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByStatus(Status status);
}
