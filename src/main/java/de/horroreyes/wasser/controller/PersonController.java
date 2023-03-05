package de.horroreyes.wasser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.services.PersonService;
import jakarta.transaction.Transactional;

@RestController
@Transactional
@RequestMapping("api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public List<Person> indexPersons() {
        return personService.getAll();
    }

    @PostMapping("/")
    public Person create(@Validated @RequestBody Person newPerson) {
        return personService.save(newPerson);
    }

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable long personId) {
        return personService.get(personId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable long personId) {
        personService.delete(personId);
    }
}
