package org.example.controller;

import org.example.model.dto.RoutesAndTransportDTO;
import org.example.service.RoutesAndTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routes-and-transport")
public class RoutesAndTransportController {

    private final RoutesAndTransportService service;

    @Autowired
    public RoutesAndTransportController(RoutesAndTransportService service) {
        this.service = service;
    }



    @GetMapping("/routes")
    public String getAllRoutes(Model model) {
        List<RoutesAndTransportDTO.RouteDTO> routes = service.getAllRoutes();
        model.addAttribute("routes", routes);
        return "routes/list";
    }

    @GetMapping("/routes/{id}")
    public String getRouteById(@PathVariable Long id, Model model) {
        RoutesAndTransportDTO.RouteDTO route = service.getRouteById(id);
        model.addAttribute("route", route);
        return "routes/detail";
    }

    @GetMapping("/routes/search/by-name")
    public String findRoutesByName(@RequestParam String name, Model model) {
        List<RoutesAndTransportDTO.RouteDTO> routes = service.findRoutesByName(name);
        model.addAttribute("routes", routes);
        return "routes/list";
    }

    @GetMapping("/routes/search/by-location")
    public String findRoutesByLocation(@RequestParam String location, Model model) {
        List<RoutesAndTransportDTO.RouteDTO> routes = service.findRoutesByLocation(location);
        model.addAttribute("routes", routes);
        return "routes/list";
    }

    @GetMapping("/routes/create")
    public String showCreateRouteForm(Model model) {
        model.addAttribute("route", new RoutesAndTransportDTO.RouteDTO());
        return "routes/create";
    }

    @PostMapping("/routes")
    public String createRoute(@ModelAttribute RoutesAndTransportDTO.RouteDTO dto, Model model) {
        RoutesAndTransportDTO.RouteDTO createdRoute = service.createRoute(dto);
        model.addAttribute("route", createdRoute);
        return "redirect:/routes-and-transport/routes/" + createdRoute.getId();
    }

    @GetMapping("/routes/{id}/edit")
    public String showUpdateRouteForm(@PathVariable Long id, Model model) {
        RoutesAndTransportDTO.RouteDTO route = service.getRouteById(id);
        model.addAttribute("route", route);
        return "routes/edit";
    }

    @PostMapping("/routes/{id}")
    public String updateRoute(@PathVariable Long id, @ModelAttribute RoutesAndTransportDTO.RouteDTO dto) {
        service.updateRoute(id, dto);
        return "redirect:/routes-and-transport/routes/" + id;
    }

    @PostMapping("/routes/{id}/delete")
    public String deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
        return "redirect:/routes-and-transport/routes";
    }



    @GetMapping("/transport")
    public String getAllTransportations(Model model) {
        List<RoutesAndTransportDTO.TransportationDTO> transportations = service.getAllTransportations();
        model.addAttribute("transportations", transportations);
        return "transport/list";
    }

    @GetMapping("/transport/{id}")
    public String getTransportationById(@PathVariable Long id, Model model) {
        RoutesAndTransportDTO.TransportationDTO transportation = service.getTransportationById(id);
        model.addAttribute("transportation", transportation);
        return "transport/detail";
    }

    @GetMapping("/transport/search/by-mode")
    public String findTransportationsByMode(@RequestParam String mode, Model model) {
        List<RoutesAndTransportDTO.TransportationDTO> transportations = service.findTransportationsByMode(mode);
        model.addAttribute("transportations", transportations);
        return "transport/list";
    }

    @GetMapping("/transport/create")
    public String showCreateTransportationForm(Model model) {
        model.addAttribute("transportation", new RoutesAndTransportDTO.TransportationDTO());
        return "transport/create";
    }

    @PostMapping("/transport")
    public String createTransportation(@ModelAttribute RoutesAndTransportDTO.TransportationDTO dto, Model model) {
        RoutesAndTransportDTO.TransportationDTO createdTransportation = service.createTransportation(dto);
        model.addAttribute("transportation", createdTransportation);
        return "redirect:/routes-and-transport/transport/" + createdTransportation.getId();
    }

    @GetMapping("/transport/{id}/edit")
    public String showUpdateTransportationForm(@PathVariable Long id, Model model) {
        RoutesAndTransportDTO.TransportationDTO transportation = service.getTransportationById(id);
        model.addAttribute("transportation", transportation);
        return "transport/edit";
    }

    @PostMapping("/transport/{id}")
    public String updateTransportation(@PathVariable Long id, @ModelAttribute RoutesAndTransportDTO.TransportationDTO dto) {
        service.updateTransportation(id, dto);
        return "redirect:/routes-and-transport/transport/" + id;
    }

    @PostMapping("/transport/{id}/delete")
    public String deleteTransportation(@PathVariable Long id) {
        service.deleteTransportation(id);
        return "redirect:/routes-and-transport/transport";
    }
}
