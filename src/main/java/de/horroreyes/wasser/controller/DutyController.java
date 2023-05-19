package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Duty;
import de.horroreyes.wasser.repositories.DutyRepository;
import de.horroreyes.wasser.services.DutyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/duties", produces = "application/json")
public class DutyController {
    private final DutyService dutyService;
    private final DutyRepository dutyRepository;

    public DutyController(DutyService dutyService,
                          DutyRepository dutyRepository) {
        this.dutyService = dutyService;
        this.dutyRepository = dutyRepository;
    }

    @GetMapping("/")
    public List<Duty> indexDutys() {
        return this.dutyService.getDutys();
    }

    @GetMapping("/today")
    public Duty getToday() {
        return this.dutyService.getToday();
    }

    @GetMapping("/{dutyId}")
    public Duty getDuty(@PathVariable Long dutyId) {
        return this.dutyService.getDuty(dutyId);
    }

    @PutMapping("/")
    public Duty updateDuty(@RequestBody Duty duty) {
        Duty existing = dutyRepository.findById(duty.getId()).orElseThrow();
        duty.setId(existing.getId());
        return this.dutyService.updateDuty(duty);
    }
}
