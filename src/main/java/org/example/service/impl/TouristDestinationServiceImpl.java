package org.example.service.impl;

import org.example.model.TouristDestination;
import org.example.model.dto.TouristDestinationDTO;
import org.example.model.exception.TouristDestinationNotFound;
import org.example.repository.TouristDestinationRepository;
import org.example.service.TouristDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TouristDestinationServiceImpl implements TouristDestinationService {
    private final TouristDestinationRepository repository;
    @Autowired
    public TouristDestinationServiceImpl(TouristDestinationRepository repository){
        this.repository=repository;
    }

    @Override
    public List<TouristDestinationDTO> getAllTouristDestinations() {
        return repository.findAll().stream()
                .map(entity -> new TouristDestinationDTO(entity.getName(), entity.getContainsPlace()))
                .collect(Collectors.toList());
    }

    @Override
    public TouristDestinationDTO getTouristDestinationById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new TouristDestinationNotFound());
        return new TouristDestinationDTO(entity.getName(), entity.getContainsPlace());
    }

    @Override
    public List<TouristDestinationDTO> findByName(String name) {
        return repository.findByContainsPlaceContainingAndName(name).stream()
                .map(entity -> new TouristDestinationDTO(entity.getName(), entity.getContainsPlace()))
                .collect(Collectors.toList());
    }

    @Override
    public TouristDestinationDTO createTouristDestination(TouristDestinationDTO dto) {
        var entity = new TouristDestination();
        entity.setName(dto.getName());
        entity.setContainsPlace(dto.getContainsPlace());
        entity = repository.save(entity);
        return new TouristDestinationDTO(entity.getName(), entity.getContainsPlace());
    }

    @Override
    public TouristDestinationDTO updateTouristDestination(Long id, TouristDestinationDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new TouristDestinationNotFound());
        entity.setName(dto.getName());
        entity.setContainsPlace(dto.getContainsPlace());
        entity = repository.save(entity);
        return new TouristDestinationDTO(entity.getName(), entity.getContainsPlace());
    }

    @Override
    public void deleteTouristDestination(Long id) {
        repository.deleteById(id);
    }
}