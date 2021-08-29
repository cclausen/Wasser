package de.horroreyes.wasser.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.services.PersonService;

@RestController
@Transactional
@RequestMapping("api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Person> all() {
        return personService.getAll();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Person newPerson(@RequestBody Person newPerson) {
        return personService.save(newPerson);
    }

}
