package org.example.controller;

import org.example.model.dto.TouristDestinationDTO;
import org.example.service.TouristDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourist-destinations")
public class TouristDestinationController {

    private final TouristDestinationService service;

    @Autowired
    public TouristDestinationController(TouristDestinationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TouristDestinationDTO>> getAllTouristDestinations() {
        List<TouristDestinationDTO> destinations = service.getAllTouristDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristDestinationDTO> getTouristDestinationById(@PathVariable Long id) {
        TouristDestinationDTO destination = service.getTouristDestinationById(id);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<List<TouristDestinationDTO>> findByName(@RequestParam String name) {
        List<TouristDestinationDTO> destinations = service.findByName(name);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

//    @GetMapping("/search/by-location")
//    public ResponseEntity<List<TouristDestinationDTO>> findByLocation(@RequestParam String location) {
//        List<TouristDestinationDTO> destinations = service.findByLocation(location);
//        return new ResponseEntity<>(destinations, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<TouristDestinationDTO> createTouristDestination(@RequestBody TouristDestinationDTO dto) {
        TouristDestinationDTO createdDestination = service.createTouristDestination(dto);
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TouristDestinationDTO> updateTouristDestination(@PathVariable Long id, @RequestBody TouristDestinationDTO dto) {
        TouristDestinationDTO updatedDestination = service.updateTouristDestination(id, dto);
        return new ResponseEntity<>(updatedDestination, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTouristDestination(@PathVariable Long id) {
        service.deleteTouristDestination(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}