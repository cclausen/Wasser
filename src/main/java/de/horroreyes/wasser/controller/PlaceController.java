package de.horroreyes.wasser.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.repositories.PlaceRepository;

@RestController
@Transactional
@RequestMapping("api/places")
public class PlaceController {
    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping
    public List<Place> all() {
        return placeRepository.findAll();
    }

    @PostMapping()
    public Place newPerson(@RequestBody Place newPlace) {
        return placeRepository.save(newPlace);
    }

}
