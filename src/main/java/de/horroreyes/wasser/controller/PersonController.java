package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.enums.Status;
import de.horroreyes.wasser.services.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Transactional
@RequestMapping(path = "api/persons", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PutMapping("/{personId}")
    public Person update(@PathVariable long personId, @Validated @RequestBody Person newPerson) {
        return personService.update(personId, newPerson);
    }

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable long personId) {
        return personService.get(personId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable long personId) {
        personService.delete(personId);
    }

    @GetMapping("/byStatus/{status}")
    public List<Person> getPersonsByStatus(@PathVariable Status status) {
        return personService.getByStatus(status);
    }
}
