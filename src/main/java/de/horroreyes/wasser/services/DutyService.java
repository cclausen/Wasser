package de.horroreyes.wasser.services;

import de.horroreyes.wasser.model.Duty;
import de.horroreyes.wasser.repositories.DutyRepository;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DutyService {
    private final DutyRepository dutyRepository;
    private final PlaceService placeService;

    public DutyService(DutyRepository dutyRepository, PlaceService placeService) {
        this.dutyRepository = dutyRepository;
        this.placeService = placeService;
    }

    public List<Duty> getDutys() {
        return dutyRepository.findAll();
    }

    @Synchronized
    public Duty getToday() {
        return dutyRepository.findByDateIs(LocalDate.now()).orElseGet(() -> {
            Duty duty = new Duty();
            duty.setDate(LocalDate.now());
            duty.setPlace(placeService.getPlace(1L));
            return dutyRepository.save(duty);
        });
    }

    public Optional<Duty> getDuty(LocalDate date) {
        return dutyRepository.findByDateIs(date);
    }

    public Duty getDuty(Long id) {
        return dutyRepository.findById(id).orElseThrow();
    }

    public Duty updateDuty(Duty duty) {
        return dutyRepository.save(duty);
    }

    public void deleteById(Long dutyId) {
        dutyRepository.deleteById(dutyId);
    }

    public Duty saveDuty(Duty duty) {
        return dutyRepository.save(duty);
    }
}
