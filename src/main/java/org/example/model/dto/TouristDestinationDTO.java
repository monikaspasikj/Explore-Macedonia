package org.example.model.dto;

import lombok.Data;
import org.example.model.enums.Place;

import java.util.List;

@Data
public class TouristDestinationDTO {
    private Long id;
    private String name;
    private List<Place> containsPlace;
    private String location;

    public TouristDestinationDTO() {}

    public TouristDestinationDTO(String name, List<Place> containsPlace) {
        this.name = name;
        this.containsPlace = containsPlace;
    }
}
