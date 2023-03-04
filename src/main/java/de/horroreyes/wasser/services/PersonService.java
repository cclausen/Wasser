package de.horroreyes.wasser.services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.repositories.PersonRepository;

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

    public Person get(long personId) {
        return personRepository.getReferenceById(personId);
    }

    public void delete(long personId) {
        personRepository.deleteById(personId);
    }
}
