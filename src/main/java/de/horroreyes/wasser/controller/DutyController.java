package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Duty;
import de.horroreyes.wasser.services.DutyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/duties", produces = "application/json")
public class DutyController {
    private final DutyService dutyService;

    public DutyController(DutyService dutyService) {
        this.dutyService = dutyService;
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
        Duty existing = dutyService.getDuty(duty.getId());
        duty.setId(existing.getId());
        return this.dutyService.updateDuty(duty);
    }

    @PostMapping("/")
    public Duty createDuty(@RequestBody Duty duty) {
        return this.dutyService.saveDuty(duty);
    }

    @DeleteMapping("/{dutyId}")
    public void deleteDuty(@PathVariable Long dutyId) {
        this.dutyService.deleteById(dutyId);
    }
}
