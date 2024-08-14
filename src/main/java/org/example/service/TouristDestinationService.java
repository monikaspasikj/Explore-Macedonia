package org.example.service;

import org.example.model.TouristDestination;
import org.example.model.dto.TouristDestinationDTO;

import java.util.List;

public interface TouristDestinationService {
    List<TouristDestinationDTO> getAllTouristDestinations();
    TouristDestinationDTO getTouristDestinationById(Long id);
    List<TouristDestinationDTO> findByName(String name);
    TouristDestinationDTO createTouristDestination(TouristDestinationDTO touristDestinationDTO);
    TouristDestinationDTO updateTouristDestination(Long id, TouristDestinationDTO touristDestinationDTO);
    void deleteTouristDestination(Long id);
}
