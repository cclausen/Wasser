package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Day;
import de.horroreyes.wasser.repositories.DayRepository;
import de.horroreyes.wasser.services.DayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/days", produces = "application/json")
public class DayController {
    private final DayService dayService;
    private final DayRepository dayRepository;

    public DayController(DayService dayService,
                         DayRepository dayRepository) {
        this.dayService = dayService;
        this.dayRepository = dayRepository;
    }

    @GetMapping("/")
    public List<Day> indexDays() {
        return this.dayService.getDays();
    }

    @GetMapping("/today")
    public Day getToday() {
        return this.dayService.getToday();
    }

    @GetMapping("/{dayId}")
    public Day getDay(@PathVariable Long dayId) {
        return this.dayService.getDay(dayId);
    }

    @PutMapping("/")
    public Day updateDay(@RequestBody Day day) {
        Day existing = dayRepository.findById(day.getId()).orElseThrow();
        day.setId(existing.getId());
        return this.dayService.updateDay(day);
    }
}
