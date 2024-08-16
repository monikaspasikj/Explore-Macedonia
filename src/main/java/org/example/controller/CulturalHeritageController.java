package org.example.controller;

import org.example.model.dto.CulturalHeritageDTO;
import org.example.service.CulturalHeritageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultural-heritages")
public class CulturalHeritageController {

    private final CulturalHeritageService service;

    @Autowired
    public CulturalHeritageController(CulturalHeritageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CulturalHeritageDTO>> getAllCulturalHeritages() {
        List<CulturalHeritageDTO> culturalHeritages = service.getAllCulturalHeritages();
        return new ResponseEntity<>(culturalHeritages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CulturalHeritageDTO> getCulturalHeritageById(@PathVariable Long id) {
        CulturalHeritageDTO culturalHeritage = service.getCulturalHeritageById(id);
        return new ResponseEntity<>(culturalHeritage, HttpStatus.OK);
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<List<CulturalHeritageDTO>> findByName(@RequestParam String name) {
        List<CulturalHeritageDTO> culturalHeritages = service.findByName(name);
        return new ResponseEntity<>(culturalHeritages, HttpStatus.OK);
    }

    @GetMapping("/search/by-location")
    public ResponseEntity<List<CulturalHeritageDTO>> findByLocation(@RequestParam String location) {
        List<CulturalHeritageDTO> culturalHeritages = service.findByLocation(location);
        return new ResponseEntity<>(culturalHeritages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CulturalHeritageDTO> createCulturalHeritage(@RequestBody CulturalHeritageDTO dto) {
        CulturalHeritageDTO createdCulturalHeritage = service.createCulturalHeritage(dto);
        return new ResponseEntity<>(createdCulturalHeritage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CulturalHeritageDTO> updateCulturalHeritage(@PathVariable Long id, @RequestBody CulturalHeritageDTO dto) {
        CulturalHeritageDTO updatedCulturalHeritage = service.updateCulturalHeritage(id, dto);
        return new ResponseEntity<>(updatedCulturalHeritage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCulturalHeritage(@PathVariable Long id) {
        service.deleteCulturalHeritage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
