package de.horroreyes.wasser.services;

import de.horroreyes.wasser.forms.GoogleForm;
import de.horroreyes.wasser.forms.HelferstundenGoogleForm;
import de.horroreyes.wasser.model.Duty;
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

    public Summary summary(Duty duty) {
        LocalDateTime dateTime = duty.getDate().atTime(0, 0, 0);

        List<Presence> presences = presenceRepository.findAllForThatPeriod(
                dateTime,
                dateTime.plusDays(1)
        );
        long total = presences.stream()
                .mapToLong(presence ->
                        {
                            LocalDateTime startTime = presence.getStart();
                            LocalDateTime endTime = Objects.requireNonNullElse(presence.getEnd(), LocalDateTime.now());
                            return Duration.between(
                                    // Start, but only from this day
                                    startTime.isAfter(dateTime) ? startTime : dateTime,
                                    // End, but only until this day
                                    endTime.isBefore(dateTime.plusDays(1)) ? endTime : dateTime.plusDays(1)
                            ).getSeconds();
                        }
                ).sum();

        Set<Person> persons = presences.stream().map(Presence::getPerson).collect(HashSet::new, HashSet::add, HashSet::addAll);
        long openPresence = presences.stream().filter(presence -> presence.getEnd() == null).count();
        return new Summary(LocalDateTime.now(), duty, persons, duty.getPlace(), presences, total, (double) total / 60 / 60, openPresence);
    }

    public boolean sendSummary(Duty duty) {
        return form.sendForm(summary(duty));
    }

    public String fillSummary(Duty duty) throws UnsupportedEncodingException {
        return form.openPrefilledForm(summary(duty));
    }
}
