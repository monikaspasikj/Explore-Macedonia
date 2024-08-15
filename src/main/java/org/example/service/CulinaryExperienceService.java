package org.example.service;

import org.example.model.CulinaryExperience;
import org.example.model.dto.CulinaryExperienceDTO;

import java.util.List;

public interface CulinaryExperienceService {
    List<CulinaryExperienceDTO> getAllCulinaryExperiences();
    CulinaryExperienceDTO getCulinaryExperienceById(Long id);
    List<CulinaryExperienceDTO> findByName(String name);
    List<CulinaryExperienceDTO> findByLocation(String location);
    CulinaryExperienceDTO createCulinaryExperience(CulinaryExperienceDTO culinaryExperienceDTO);
    CulinaryExperienceDTO updateCulinaryExperience(Long id, CulinaryExperienceDTO culinaryExperienceDTO);
    void deleteCulinaryExperience(Long id);
}