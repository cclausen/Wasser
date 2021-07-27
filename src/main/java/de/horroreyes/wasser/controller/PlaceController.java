package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.repositories.PlaceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("places")
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
