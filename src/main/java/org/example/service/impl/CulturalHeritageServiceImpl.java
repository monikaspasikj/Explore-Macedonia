package org.example.service.impl;

import org.example.model.CulturalHeritage;
import org.example.model.dto.CulturalHeritageDTO;
import org.example.model.exception.CulturalHeritageNotFound;
import org.example.repository.CulturalHeritageRepository;
import org.example.service.CulturalHeritageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CulturalHeritageServiceImpl implements CulturalHeritageService {


    private final CulturalHeritageRepository repository;

    @Autowired
    public CulturalHeritageServiceImpl(CulturalHeritageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CulturalHeritageDTO> getAllCulturalHeritages() {
        return repository.findAll().stream()
                .map(entity -> new CulturalHeritageDTO(entity.getName(), entity.getDescription(), entity.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public CulturalHeritageDTO getCulturalHeritageById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new CulturalHeritageNotFound());
        return new CulturalHeritageDTO(entity.getName(), entity.getDescription(), entity.getLocation());
    }

    @Override
    public List<CulturalHeritageDTO> findByName(String name) {
        return repository.findByNameAndDescriptionAndLocation(name, null, null).stream()
                .map(entity -> new CulturalHeritageDTO(entity.getName(), entity.getDescription(), entity.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CulturalHeritageDTO> findByLocation(String location) {
        return repository.findByLocation(location).stream()
                .map(entity -> new CulturalHeritageDTO(entity.getName(), entity.getDescription(), entity.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public CulturalHeritageDTO createCulturalHeritage(CulturalHeritageDTO dto) {
        var entity = new CulturalHeritage();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity = repository.save(entity);
        return new CulturalHeritageDTO(entity.getName(), entity.getDescription(), entity.getLocation());
    }

    @Override
    public CulturalHeritageDTO updateCulturalHeritage(Long id, CulturalHeritageDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new CulturalHeritageNotFound());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity = repository.save(entity);
        return new CulturalHeritageDTO(entity.getName(), entity.getDescription(), entity.getLocation());
    }

    @Override
    public void deleteCulturalHeritage(Long id) {
        repository.deleteById(id);
    }
}