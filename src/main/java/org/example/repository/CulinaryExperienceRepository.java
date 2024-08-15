package org.example.repository;

import org.example.model.CulinaryExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CulinaryExperienceRepository extends JpaRepository<CulinaryExperience, Long> {

    List<CulinaryExperience> findByNameAndDescriptionAndLocation(String name, String description, String location);

    List<CulinaryExperience> findByLocation(String location);


    List<CulinaryExperience> findByName(String name);
}
