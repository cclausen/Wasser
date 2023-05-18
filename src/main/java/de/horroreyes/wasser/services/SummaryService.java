package de.horroreyes.wasser.services;

import de.horroreyes.wasser.model.*;
import de.horroreyes.wasser.repositories.PresenceRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class SummaryService {
    private final PresenceRepository presenceRepository;

    public SummaryService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    public Summary summary() {
        Place place = new Place();
        place.setName("Bultensee");
        Day day = new Day();
        day.setPlace(place);

        List<Presence> presences = presenceRepository.findAllByStartAfterAndEndBeforeOrEndIsNull(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0),
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).plusDays(1)
        );
        long total = presences.stream()
                .mapToLong(presence ->
                        Duration.between(
                                presence.getStart(),
                                Objects.requireNonNullElse(presence.getEnd(), LocalDateTime.now())
                        ).getSeconds()
                ).sum();
        Set<Person> persons = presences.stream().map(Presence::getPerson).collect(HashSet::new, HashSet::add, HashSet::addAll);
        long openPresence = presences.stream().filter(presence -> presence.getEnd() == null).count();
        return new Summary(LocalDateTime.now(), day, persons, place, presences, total, (double) total / 60 / 60, openPresence);
    }
}
