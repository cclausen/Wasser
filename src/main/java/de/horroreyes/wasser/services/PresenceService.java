package de.horroreyes.wasser.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.horroreyes.wasser.exceptions.MoreThanOneOpenPresenceException;
import de.horroreyes.wasser.exceptions.NoOpenPresenceException;
import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.model.Presence;
import de.horroreyes.wasser.repositories.PersonRepository;
import de.horroreyes.wasser.repositories.PlaceRepository;
import de.horroreyes.wasser.repositories.PresenceRepository;

@Service
public class PresenceService {
    private final PresenceRepository presenceRepository;
    private final PlaceRepository placeRepository;
    private final PersonRepository personRepository;

    public PresenceService(PresenceRepository presenceRepository, PlaceRepository placeRepository,
                           PersonRepository personRepository) {
        this.presenceRepository = presenceRepository;
        this.placeRepository = placeRepository;
        this.personRepository = personRepository;
    }

    public void startPresence(long personId) {
        Person person = personRepository.findById(personId).orElseThrow();
        List<Presence> unstoppedPresences = presenceRepository.findByPersonAndEnd(person, null);
        if (unstoppedPresences.isEmpty()) {
            presenceRepository.save(new Presence(person, LocalDateTime.now()));
        }
    }

    public void stopPresence(long presenceId) {
        Presence presence = presenceRepository.findById(presenceId).orElseThrow();
        presence.setEnd(LocalDateTime.now());
        presenceRepository.save(presence);
    }

    public void stopPresence(long personId, long placeId) {
        Presence presence = getOpenPresence(personId, placeId);
        presence.setEnd(LocalDateTime.now());
        presenceRepository.save(presence);
    }

    public Presence getOpenPresence(long personId, long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow();
        Person person = personRepository.findById(personId).orElseThrow();
        List<Presence> unstoppedPresences = presenceRepository.findByPersonAndPlaceAndEnd(person, place, null);
        if (unstoppedPresences.isEmpty()) {
            throw new NoOpenPresenceException("Cannot identify correct presence to stop");
        }

        if (unstoppedPresences.size() > 1) {
            throw new MoreThanOneOpenPresenceException("Cannot identify correct presence to stop");
        }
        return unstoppedPresences.get(0);
    }

    public List<Presence> findAllByPersonId(long personId) {
        return presenceRepository.findAllByPersonId(personId);
    }

    public Optional<Presence> findOpenPresenceByPersonId(long personId) {
        return presenceRepository.findActivePresenceByStartIsNotNullAndEndIsNullAndPersonId(personId);
    }

    public List<Presence> getOpenPresences() {
        return presenceRepository.findByEndIsNull();
    }

    public void stopPresenceByPersonId(long personId) {
        Optional<Presence> presence = presenceRepository.findActivePresenceByStartIsNotNullAndEndIsNullAndPersonId(personId);
        if (presence.isPresent()) {
            presence.get().setEnd(LocalDateTime.now());
            presenceRepository.save(presence.get());
        }
    }
}
