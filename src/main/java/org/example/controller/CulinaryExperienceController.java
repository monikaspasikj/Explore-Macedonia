package org.example.controller;

import org.example.model.dto.CulinaryExperienceDTO;
import org.example.service.CulinaryExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/culinary-experiences")
public class CulinaryExperienceController {

    private final CulinaryExperienceService service;

    @Autowired
    public CulinaryExperienceController(CulinaryExperienceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CulinaryExperienceDTO>> getAllCulinaryExperiences() {
        List<CulinaryExperienceDTO> experiences = service.getAllCulinaryExperiences();
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CulinaryExperienceDTO> getCulinaryExperienceById(@PathVariable Long id) {
        CulinaryExperienceDTO experience = service.getCulinaryExperienceById(id);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<List<CulinaryExperienceDTO>> findByName(@RequestParam String name) {
        List<CulinaryExperienceDTO> experiences = service.findByName(name);
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @GetMapping("/search/by-location")
    public ResponseEntity<List<CulinaryExperienceDTO>> findByLocation(@RequestParam String location) {
        List<CulinaryExperienceDTO> experiences = service.findByLocation(location);
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CulinaryExperienceDTO> createCulinaryExperience(@RequestBody CulinaryExperienceDTO dto) {
        CulinaryExperienceDTO createdExperience = service.createCulinaryExperience(dto);
        return new ResponseEntity<>(createdExperience, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CulinaryExperienceDTO> updateCulinaryExperience(@PathVariable Long id, @RequestBody CulinaryExperienceDTO dto) {
        CulinaryExperienceDTO updatedExperience = service.updateCulinaryExperience(id, dto);
        return new ResponseEntity<>(updatedExperience, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCulinaryExperience(@PathVariable Long id) {
        service.deleteCulinaryExperience(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}