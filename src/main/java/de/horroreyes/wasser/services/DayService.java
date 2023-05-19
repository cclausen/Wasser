package de.horroreyes.wasser.services;

import de.horroreyes.wasser.model.Day;
import de.horroreyes.wasser.repositories.DayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {
    private final DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<Day> getDays() {
        return dayRepository.findAll();
    }

    public Day getToday() {
        return dayRepository.findByDateIs(LocalDate.now()).orElseGet(() -> {
            Day day = new Day();
            day.setDate(LocalDate.now());
            return dayRepository.save(day);
        });
    }

    public Optional<Day> getDay(LocalDate date) {
        return dayRepository.findByDateIs(date);
    }

    public Day getDay(Long id) {
        return dayRepository.findById(id).orElseThrow();
    }

    public Day updateDay(Day day) {
        return dayRepository.save(day);
    }
}
