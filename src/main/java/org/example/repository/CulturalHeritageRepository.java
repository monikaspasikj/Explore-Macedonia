package org.example.repository;

import org.example.model.CulturalHeritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CulturalHeritageRepository extends JpaRepository<CulturalHeritage, Long> {

//    List<CulturalHeritage> findAllBy();
//
//    List<CulturalHeritage> findByEventNameAndDescriptionAndLocation(String name, String description, String location);
//
//    List<CulturalHeritage> findByCustomNameAndDescriptionAndLocation(String name, String description, String location);
//
//    List<CulturalHeritage> findByLocation(String location);
//
//    List<CulturalHeritage> findByEventLocation(String location);

    List<CulturalHeritage> findByNameAndDescriptionAndLocation(String name, String description, String location);
    List<CulturalHeritage> findByLocation(String location);
}
