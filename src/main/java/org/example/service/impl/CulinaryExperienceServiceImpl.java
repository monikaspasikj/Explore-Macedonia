package org.example.service.impl;

import org.example.model.CulinaryExperience;
import org.example.model.dto.CulinaryExperienceDTO;
import org.example.model.exception.CulinaryExperienceNotFound;
import org.example.repository.CulinaryExperienceRepository;
import org.example.service.CulinaryExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CulinaryExperienceServiceImpl implements CulinaryExperienceService {

    private final CulinaryExperienceRepository repository;
    @Autowired
    public CulinaryExperienceServiceImpl (CulinaryExperienceRepository repository){
        this.repository=repository;
    }

    @Override
    public List<CulinaryExperienceDTO> getAllCulinaryExperiences() {
        return repository.findAll().stream()
                .map(entity -> new CulinaryExperienceDTO(entity.getName(), entity.getLocation(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public CulinaryExperienceDTO getCulinaryExperienceById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new CulinaryExperienceNotFound());
        return new CulinaryExperienceDTO(entity.getName(), entity.getLocation(), entity.getDescription());
    }

    @Override
    public List<CulinaryExperienceDTO> findByName(String name) {
        return repository.findByName(name).stream()
                .map(entity -> new CulinaryExperienceDTO(entity.getName(), entity.getLocation(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CulinaryExperienceDTO> findByLocation(String location) {
        return repository.findByLocation(location).stream()
                .map(entity -> new CulinaryExperienceDTO(entity.getName(), entity.getLocation(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public CulinaryExperienceDTO createCulinaryExperience(CulinaryExperienceDTO dto) {
        var entity = new CulinaryExperience();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity = repository.save(entity);
        return new CulinaryExperienceDTO(entity.getName(), entity.getLocation(), entity.getDescription());
    }

    @Override
    public CulinaryExperienceDTO updateCulinaryExperience(Long id, CulinaryExperienceDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new CulinaryExperienceNotFound());
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity = repository.save(entity);
        return new CulinaryExperienceDTO(entity.getName(), entity.getLocation(), entity.getDescription());
    }

    @Override
    public void deleteCulinaryExperience(Long id) {
        repository.deleteById(id);
    }
}
