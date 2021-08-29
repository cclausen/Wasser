package de.horroreyes.wasser.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    public List<Presence> all() {
        return presenceRepository.findAll();
    }

    @PostMapping("start")
    @PreAuthorize("hasRole('USER')")
    public void startPresence(@RequestParam long personId, @RequestParam long placeId) {
        presenceService.startPresence(personId, placeId);
    }

    @PostMapping("stopById")
    @PreAuthorize("hasRole('USER')")
    public void stopPresence(@RequestParam long presenceId) {
        presenceService.stopPresence(presenceId);
    }

    @PostMapping("stop")
    @PreAuthorize("hasRole('USER')")
    public void stopPresence(@RequestParam long personId, @RequestParam long placeId) {
        presenceService.stopPresence(personId, placeId);
    }

    @GetMapping("/{personId}")
    @PreAuthorize("hasRole('USER')")
    public List<Presence> allByPerson(@PathVariable long personId) {
        return presenceRepository.findAllByPersonId(personId);
    }
}
