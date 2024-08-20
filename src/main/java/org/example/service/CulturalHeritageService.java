package org.example.service;

import org.example.model.dto.CulturalHeritageDTO;

import java.util.List;

public interface CulturalHeritageService {
    List<CulturalHeritageDTO> getAllCulturalHeritages();
    CulturalHeritageDTO getCulturalHeritageById(Long id);
    List<CulturalHeritageDTO> findByName(String name);
    List<CulturalHeritageDTO> findByLocation(String location);
    CulturalHeritageDTO createCulturalHeritage(CulturalHeritageDTO culturalHeritageDTO);
    CulturalHeritageDTO updateCulturalHeritage(Long id, CulturalHeritageDTO culturalHeritageDTO);
    void deleteCulturalHeritage(Long id);
}