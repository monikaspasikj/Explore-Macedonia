package org.example.service;

import org.example.model.dto.TouristDestinationDTO;

import java.util.List;

public interface TouristDestinationService {
    List<TouristDestinationDTO> getAllTouristDestinations();
    TouristDestinationDTO getTouristDestinationById(Long id);
    List<TouristDestinationDTO> findByName(String name);
    List<TouristDestinationDTO> findByPlaceAndName(String containsPlace, String name);
    List<TouristDestinationDTO> findByLocation(String location);
    TouristDestinationDTO createTouristDestination(TouristDestinationDTO touristDestinationDTO);
    TouristDestinationDTO updateTouristDestination(Long id, TouristDestinationDTO touristDestinationDTO);
    void deleteTouristDestination(Long id);
}
