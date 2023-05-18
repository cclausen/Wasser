package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Achievement;
import de.horroreyes.wasser.services.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/achievements", produces = MediaType.APPLICATION_JSON_VALUE)
public class AchievementController {
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/all")
    public List<Achievement> getAll() {
        return achievementService.getAll();
    }

    @GetMapping("/{achievementId}")
    public Achievement get(@PathVariable long achievementId) {
        return achievementService.get(achievementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byPerson/{personId}")
    public List<Achievement> getAllByPersonId(@PathVariable long personId) {
        return achievementService.getAllByPersonId(personId);
    }

    @PostMapping("/")
    public Achievement save(@RequestBody Achievement newAchievement) {
        return achievementService.save(newAchievement);
    }

    @DeleteMapping("/{achievementId}")
    public void delete(@PathVariable long achievementId) {
        achievementService.delete(achievementId);
    }

}
