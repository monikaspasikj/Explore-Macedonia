package org.example.model.dto;

import lombok.Data;

@Data
public class CulturalHeritageDTO {
    private Long id;
    private String name;
    private String description;
    private String location;

    public CulturalHeritageDTO() {}
    public CulturalHeritageDTO(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }
}
