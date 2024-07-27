package org.example.model;

import lombok.Data;

@Data
public class RoutesAndTransport {
    @Data
    public class Route {
        private String name;
        private String location;
        private String description;
    }
    @Data
    public class Transportation {
        private String transportationMode;
        private String description;
    }
}

