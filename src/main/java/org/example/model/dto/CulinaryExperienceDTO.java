package org.example.model.dto;

import lombok.Data;

@Data
public class CulinaryExperienceDTO {
    private String name;
    private String location;
    private String description;
    public CulinaryExperienceDTO(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }
}
