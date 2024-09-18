package org.example.repository;

import org.example.model.TouristDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristDestinationRepository extends JpaRepository<TouristDestination, Long> {

    // Find destinations by name
    List<TouristDestination> findByName(String name);

    // Find destinations by name and place
    List<TouristDestination> findByContainsPlaceContainingAndName(String containsPlace, String name);

    // Find destinations by location
    List<TouristDestination> findByLocation(String location);
}
