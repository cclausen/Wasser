package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    List<Person> all() {
        return personRepository.findAll();
    }

    @PostMapping()
    Person newPerson(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

}
