package org.example.controller;

import org.example.model.dto.RoutesAndTransportDTO;
import org.example.service.RoutesAndTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes-and-transport")
public class RoutesAndTransportController {

    private final RoutesAndTransportService service;

    @Autowired
    public RoutesAndTransportController(RoutesAndTransportService service) {
        this.service = service;
    }

    // Routes Endpoints

    @GetMapping("/routes")
    public ResponseEntity<List<RoutesAndTransportDTO.RouteDTO>> getAllRoutes() {
        List<RoutesAndTransportDTO.RouteDTO> routes = service.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<RoutesAndTransportDTO.RouteDTO> getRouteById(@PathVariable Long id) {
        RoutesAndTransportDTO.RouteDTO route = service.getRouteById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping("/routes/search/by-name")
    public ResponseEntity<List<RoutesAndTransportDTO.RouteDTO>> findRoutesByName(@RequestParam String name) {
        List<RoutesAndTransportDTO.RouteDTO> routes = service.findRoutesByName(name);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/routes/search/by-location")
    public ResponseEntity<List<RoutesAndTransportDTO.RouteDTO>> findRoutesByLocation(@RequestParam String location) {
        List<RoutesAndTransportDTO.RouteDTO> routes = service.findRoutesByLocation(location);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @PostMapping("/routes")
    public ResponseEntity<RoutesAndTransportDTO.RouteDTO> createRoute(@RequestBody RoutesAndTransportDTO.RouteDTO dto) {
        RoutesAndTransportDTO.RouteDTO createdRoute = service.createRoute(dto);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<RoutesAndTransportDTO.RouteDTO> updateRoute(@PathVariable Long id, @RequestBody RoutesAndTransportDTO.RouteDTO dto) {
        RoutesAndTransportDTO.RouteDTO updatedRoute = service.updateRoute(id, dto);
        return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Transportation Endpoints

    @GetMapping("/transport")
    public ResponseEntity<List<RoutesAndTransportDTO.TransportationDTO>> getAllTransportations() {
        List<RoutesAndTransportDTO.TransportationDTO> transportations = service.getAllTransportations();
        return new ResponseEntity<>(transportations, HttpStatus.OK);
    }

    @GetMapping("/transport/{id}")
    public ResponseEntity<RoutesAndTransportDTO.TransportationDTO> getTransportationById(@PathVariable Long id) {
        RoutesAndTransportDTO.TransportationDTO transportation = service.getTransportationById(id);
        return new ResponseEntity<>(transportation, HttpStatus.OK);
    }

    @GetMapping("/transport/search/by-mode")
    public ResponseEntity<List<RoutesAndTransportDTO.TransportationDTO>> findTransportationsByMode(@RequestParam String mode) {
        List<RoutesAndTransportDTO.TransportationDTO> transportations = service.findTransportationsByMode(mode);
        return new ResponseEntity<>(transportations, HttpStatus.OK);
    }

    @PostMapping("/transport")
    public ResponseEntity<RoutesAndTransportDTO.TransportationDTO> createTransportation(@RequestBody RoutesAndTransportDTO.TransportationDTO dto) {
        RoutesAndTransportDTO.TransportationDTO createdTransportation = service.createTransportation(dto);
        return new ResponseEntity<>(createdTransportation, HttpStatus.CREATED);
    }

    @PutMapping("/transport/{id}")
    public ResponseEntity<RoutesAndTransportDTO.TransportationDTO> updateTransportation(@PathVariable Long id, @RequestBody RoutesAndTransportDTO.TransportationDTO dto) {
        RoutesAndTransportDTO.TransportationDTO updatedTransportation = service.updateTransportation(id, dto);
        return new ResponseEntity<>(updatedTransportation, HttpStatus.OK);
    }

    @DeleteMapping("/transport/{id}")
    public ResponseEntity<Void> deleteTransportation(@PathVariable Long id) {
        service.deleteTransportation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
