package org.example.model.dto;

import lombok.Data;

@Data
public class CulturalHeritageDTO {
    private String name;
    private String description;
    private String location;

    public CulturalHeritageDTO(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }
}
