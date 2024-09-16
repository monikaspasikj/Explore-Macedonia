package org.example.model.dto;

import lombok.Data;

import lombok.Data;

@Data
public class RoutesAndTransportDTO {

    @Data
    public static class RouteDTO {
        private Long id;
        private String name;
        private String location;
        private String description;
        public RouteDTO() {}

        public RouteDTO(String name, String location, String description) {
            this.name = name;
            this.location = location;
            this.description = description;
        }
    }

    @Data
    public static class TransportationDTO {
        private Long id;
        private String transportationMode;
        private String description;

        public TransportationDTO() {}
        public TransportationDTO(String transportationMode, String description) {
            this.transportationMode = transportationMode;
            this.description = description;
        }
    }
}
