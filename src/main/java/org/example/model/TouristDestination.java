package org.example.model;

import lombok.Data;
import org.example.model.enums.Place;

import java.util.List;

@Data
public class TouristDestination {
    private String name;
    private List<Place> containsPlace;

}

