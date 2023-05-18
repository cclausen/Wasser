package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Place;
import de.horroreyes.wasser.services.PlaceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/places", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/")
    public List<Place> indexPlaces() {
        return this.placeService.getPlaces();
    }

    @GetMapping("/{placeId}")
    public Place getPlace(@PathVariable Long placeId) {
        return this.placeService.getPlace(placeId);
    }

    @PutMapping("/")
    public Place updatePlace(Place place) {
        return this.placeService.updatePlace(place);
    }

    @PostMapping("/")
    public Place createPlace(Place place) {
        return this.placeService.createPlace(place);
    }

    @DeleteMapping("/{placeId}")
    public void deletePlace(@PathVariable Long placeId) {
        this.placeService.deletePlace(placeId);
    }
}
