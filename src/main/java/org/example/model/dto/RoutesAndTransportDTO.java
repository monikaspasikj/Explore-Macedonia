package org.example.model.dto;

import lombok.Data;

import lombok.Data;

@Data
public class RoutesAndTransportDTO {

    @Data
    public static class RouteDTO {
        private String name;
        private String location;
        private String description;
    }

    @Data
    public static class TransportationDTO {
        private String transportationMode;
        private String description;
    }
}
