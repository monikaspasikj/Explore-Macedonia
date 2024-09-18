package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.model.enums.Place;

import java.util.List;
@Entity
@Data
public class TouristDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    private List<Place> containsPlace;
    private String location;

}

