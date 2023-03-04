package de.horroreyes.wasser.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.horroreyes.wasser.model.Presence;
import de.horroreyes.wasser.repositories.PresenceRepository;
import de.horroreyes.wasser.services.PresenceService;

@RestController
@RequestMapping("api/presences")
public class PresenceController {
    private final PresenceRepository presenceRepository;
    private final PresenceService presenceService;

    public PresenceController(PresenceRepository presenceRepository, PresenceService presenceService) {
        this.presenceRepository = presenceRepository;
        this.presenceService = presenceService;
    }

    @GetMapping
//    @PreAuthorize("hasRole('USER')")
    public List<Presence> all() {
        return presenceRepository.findAll();
    }

    @PostMapping("start")
    public void startPresence(@RequestParam long personId) {
        presenceService.startPresence(personId);
    }

    @PostMapping("stopById")
    public void stopPresence(@RequestParam long presenceId) {
        presenceService.stopPresence(presenceId);
    }

    @PostMapping("stop")
    public void stopPresenceByUser(@RequestParam long personId) {
        presenceService.stopPresence(personId);
    }

    @GetMapping("/{personId}")
    public List<Presence> allByPerson(@PathVariable long personId) {
        return presenceRepository.findAllByPersonId(personId);
    }
}
