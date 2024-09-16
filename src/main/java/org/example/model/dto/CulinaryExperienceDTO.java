package org.example.model.dto;

import lombok.Data;

@Data
public class CulinaryExperienceDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    public CulinaryExperienceDTO() {}
    public CulinaryExperienceDTO(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

}
