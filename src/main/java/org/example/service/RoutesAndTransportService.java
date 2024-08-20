package org.example.service;

import org.example.model.dto.RoutesAndTransportDTO;

import java.util.List;

public interface RoutesAndTransportService {
    List<RoutesAndTransportDTO.RouteDTO> getAllRoutes();
    RoutesAndTransportDTO.RouteDTO getRouteById(Long id);
    List<RoutesAndTransportDTO.RouteDTO> findRoutesByName(String name);
    List<RoutesAndTransportDTO.RouteDTO> findRoutesByLocation(String location);

    List<RoutesAndTransportDTO.TransportationDTO> getAllTransportations();
    RoutesAndTransportDTO.TransportationDTO getTransportationById(Long id);
    List<RoutesAndTransportDTO.TransportationDTO> findTransportationsByMode(String mode);

    RoutesAndTransportDTO.RouteDTO createRoute(RoutesAndTransportDTO.RouteDTO routeDTO);
    RoutesAndTransportDTO.TransportationDTO createTransportation(RoutesAndTransportDTO.TransportationDTO transportationDTO);

    RoutesAndTransportDTO.RouteDTO updateRoute(Long id, RoutesAndTransportDTO.RouteDTO routeDTO);
    RoutesAndTransportDTO.TransportationDTO updateTransportation(Long id, RoutesAndTransportDTO.TransportationDTO transportationDTO);

    void deleteRoute(Long id);
    void deleteTransportation(Long id);
}
