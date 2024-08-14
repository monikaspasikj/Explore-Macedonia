package org.example.service.impl;

import org.example.model.RoutesAndTransport;
import org.example.model.dto.RoutesAndTransportDTO;
import org.example.model.exception.RoutesAndTransportNotFound;
import org.example.repository.RouteRepository;
import org.example.repository.TransportationRepository;
import org.example.service.RoutesAndTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutesAndTransportServiceImpl implements RoutesAndTransportService {

    private final RouteRepository routeRepository;
    private final TransportationRepository transportationRepository;

    @Autowired
    public RoutesAndTransportServiceImpl(RouteRepository routeRepository, TransportationRepository transportationRepository){
        this.routeRepository=routeRepository;
        this.transportationRepository=transportationRepository;
    }

    @Override
    public List<RoutesAndTransportDTO.RouteDTO> getAllRoutes() {
        return routeRepository.findAllBy().stream()
                .map(entity -> new RoutesAndTransportDTO.RouteDTO(entity.getName(), entity.getLocation(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public RoutesAndTransportDTO.RouteDTO getRouteById(Long id) {
        var entity = routeRepository.findById(id).orElseThrow(() -> new RoutesAndTransportNotFound());
        return new RoutesAndTransportDTO.RouteDTO(entity.getName(), entity.getLocation(), entity.getDescription());
    }

    @Override
    public List<RoutesAndTransportDTO.RouteDTO> findRoutesByName(String name) {
        return routeRepository.findByNameIn(List.of(name)).stream()
                .map(entity -> new RoutesAndTransportDTO.RouteDTO(entity.getName(), entity.getLocation(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoutesAndTransportDTO.RouteDTO> findRoutesByLocation(String location) {
        return routeRepository.findByLocation(location).stream()
                .map(entity -> new RoutesAndTransportDTO.RouteDTO(entity.getName(), entity.getLocation(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoutesAndTransportDTO.TransportationDTO> getAllTransportations() {
        return transportationRepository.findAllBy().stream()
                .map(entity -> new RoutesAndTransportDTO.TransportationDTO(entity.getTransportationMode(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public RoutesAndTransportDTO.TransportationDTO getTransportationById(Long id) {
        var entity = transportationRepository.findById(id).orElseThrow(() -> new RoutesAndTransportNotFound());
        return new RoutesAndTransportDTO.TransportationDTO(entity.getTransportationMode(), entity.getDescription());
    }

    @Override
    public List<RoutesAndTransportDTO.TransportationDTO> findTransportationsByMode(String mode) {
        return transportationRepository.findByTransportationMode(mode).stream()
                .map(entity -> new RoutesAndTransportDTO.TransportationDTO(entity.getTransportationMode(), entity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public RoutesAndTransportDTO.RouteDTO createRoute(RoutesAndTransportDTO.RouteDTO dto) {
        var entity = new RoutesAndTransport.Route();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity = routeRepository.save(entity);
        return new RoutesAndTransportDTO.RouteDTO(entity.getName(), entity.getLocation(), entity.getDescription());
    }

    @Override
    public RoutesAndTransportDTO.TransportationDTO createTransportation(RoutesAndTransportDTO.TransportationDTO dto) {
        var entity = new RoutesAndTransport.Transportation();
        entity.setTransportationMode(dto.getTransportationMode());
        entity.setDescription(dto.getDescription());
        entity = transportationRepository.save(entity);
        return new RoutesAndTransportDTO.TransportationDTO(entity.getTransportationMode(), entity.getDescription());
    }

    @Override
    public RoutesAndTransportDTO.RouteDTO updateRoute(Long id, RoutesAndTransportDTO.RouteDTO dto) {
        var entity = routeRepository.findById(id).orElseThrow(() -> new RoutesAndTransportNotFound());
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity = routeRepository.save(entity);
        return new RoutesAndTransportDTO.RouteDTO(entity.getName(), entity.getLocation(), entity.getDescription());
    }

    @Override
    public RoutesAndTransportDTO.TransportationDTO updateTransportation(Long id, RoutesAndTransportDTO.TransportationDTO dto) {
        var entity = transportationRepository.findById(id).orElseThrow(() -> new RoutesAndTransportNotFound());
        entity.setTransportationMode(dto.getTransportationMode());
        entity.setDescription(dto.getDescription());
        entity = transportationRepository.save(entity);
        return new RoutesAndTransportDTO.TransportationDTO(entity.getTransportationMode(), entity.getDescription());
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public void deleteTransportation(Long id) {
        transportationRepository.deleteById(id);
    }
}
