package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.services.PersonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
//    @PreAuthorize("hasRole('USER')")
    public List<Person> all() {
        return personService.getAll();
    }

    @PostMapping()
//    @PreAuthorize("hasRole('ADMIN')")
    public Person newPerson(@Validated @RequestBody Person newPerson) {
        return personService.save(newPerson);
    }

}
