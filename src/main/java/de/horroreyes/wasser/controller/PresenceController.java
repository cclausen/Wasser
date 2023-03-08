package de.horroreyes.wasser.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.horroreyes.wasser.model.Presence;
import de.horroreyes.wasser.services.PresenceService;

@RestController
@RequestMapping("api/presences")
public class PresenceController {
    private final PresenceService presenceService;

    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @GetMapping
    public List<Presence> allOpen() {
        return presenceService.getOpenPresences();
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
        presenceService.stopPresenceByPersonId(personId);
    }

    @PostMapping("stopHere")
    public void stopPresenceByUser(@RequestParam long personId, @RequestParam long placeId) {
        presenceService.stopPresence(personId, placeId);
    }

    @GetMapping("/{personId}")
    public List<Presence> allByPerson(@PathVariable long personId) {
        return presenceService.findAllByPersonId(personId);
    }

    @GetMapping("open/{personId}")
    public Optional<Presence> openPresenceByPerson(@PathVariable long personId) {
        return presenceService.findOpenPresenceByPersonId(personId);
    }
}
