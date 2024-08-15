package org.example.repository;

import org.example.model.TouristDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristDestinationRepository extends JpaRepository<TouristDestination, Long> {

    List<TouristDestination> findByContainsPlaceContainingAndName(String name);

    List<TouristDestination> findByContainsPlaceContainingAndNameAndContainsPlaceType(String name, String type);;

    List<TouristDestination> findByLocation(String location);
}
