package de.horroreyes.wasser.services;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.enums.Status;
import de.horroreyes.wasser.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person save(Person newPerson) {
        return personRepository.save(newPerson);
    }

    public Optional<Person> get(long personId) {
        return personRepository.findById(personId);
    }

    public void delete(long personId) {
        personRepository.deleteById(personId);
    }

    public Person update(long personId, Person newPerson) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            Person personToUpdate = person.get();
            personToUpdate.setFirstname(newPerson.getFirstname());
            personToUpdate.setLastname(newPerson.getLastname());
            personToUpdate.setFitness(newPerson.getFitness());
            personToUpdate.setLifeguard(newPerson.getLifeguard());
            personToUpdate.setLifeguardFrom(newPerson.getLifeguardFrom());
            personToUpdate.setStatus(newPerson.getStatus());
            return personRepository.save(personToUpdate);
        } else {
            return null;
        }
    }

    public List<Person> getByStatus(Status status) {
        return personRepository.findByStatus(status);
    }
}
