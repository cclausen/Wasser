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
        List<Person> persons = personRepository.findAll();
        persons.forEach(this::setPresence);
        return persons;
    }

    private void setPresence(Person p) {
        p.setPresence(true);
    }

    public Person save(Person newPerson) {
        return personRepository.save(newPerson);
    }
}
