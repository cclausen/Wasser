package de.horroreyes.wasser.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.repositories.PersonRepository;

@RestController
@Transactional
@RequestMapping("api/persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> all() {
        return personRepository.findAll();
    }

    @PostMapping()
    public Person newPerson(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

}
