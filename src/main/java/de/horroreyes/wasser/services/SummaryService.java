package de.horroreyes.wasser.services;

import de.horroreyes.wasser.forms.GoogleForm;
import de.horroreyes.wasser.forms.HelferstundenGoogleForm;
import de.horroreyes.wasser.model.Day;
import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Presence;
import de.horroreyes.wasser.model.Summary;
import de.horroreyes.wasser.repositories.PresenceRepository;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class SummaryService {
    private final PresenceRepository presenceRepository;
    private final GoogleForm form;

    public SummaryService(PresenceRepository presenceRepository, HelferstundenGoogleForm form) {
        this.presenceRepository = presenceRepository;
        this.form = form;
    }

    public Summary summary(Day day) {
        LocalDateTime dateTime = day.getDate().atTime(0, 0, 0);
        List<Presence> presences = presenceRepository.findAllByStartAfterAndEndBeforeOrEndIsNull(
                dateTime.withHour(0).withMinute(0).withSecond(0),
                dateTime.withHour(0).withMinute(0).withSecond(0).plusDays(1)
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
        return new Summary(LocalDateTime.now(), day, persons, day.getPlace(), presences, total, (double) total / 60 / 60, openPresence);
    }

    public boolean sendSummary(Day day) {
        return form.sendForm(summary(day));
    }

    public String fillSummary(Day day) throws UnsupportedEncodingException {
        return form.openPrefilledForm(summary(day));
    }
}
