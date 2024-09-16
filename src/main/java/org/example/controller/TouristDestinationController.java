package org.example.controller;

import org.example.model.dto.TouristDestinationDTO;
import org.example.service.TouristDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tourist-destinations")
public class TouristDestinationController {

    private final TouristDestinationService service;

    @Autowired
    public TouristDestinationController(TouristDestinationService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllTouristDestinations(Model model) {
        List<TouristDestinationDTO> destinations = service.getAllTouristDestinations();
        model.addAttribute("destinations", destinations);
        return "destinations/list";
    }

    @GetMapping("/{id}")
    public String getTouristDestinationById(@PathVariable Long id, Model model) {
        TouristDestinationDTO destination = service.getTouristDestinationById(id);
        model.addAttribute("destination", destination);
        return "destinations/detail";
    }

    @GetMapping("/search/by-name")
    public String findByName(@RequestParam String name, Model model) {
        List<TouristDestinationDTO> destinations = service.findByName(name);
        model.addAttribute("destinations", destinations);
        return "destinations/list";
    }

    @GetMapping("/create")
    public String showCreateDestinationForm(Model model) {
        model.addAttribute("destination", new TouristDestinationDTO());
        return "destinations/create";
    }

    @PostMapping
    public String createTouristDestination(@ModelAttribute TouristDestinationDTO dto) {
        TouristDestinationDTO createdDestination = service.createTouristDestination(dto);
        return "redirect:/tourist-destinations/" + createdDestination.getId();
    }

    @GetMapping("/{id}/edit")
    public String showUpdateDestinationForm(@PathVariable Long id, Model model) {
        TouristDestinationDTO destination = service.getTouristDestinationById(id);
        model.addAttribute("destination", destination);
        return "destinations/edit";
    }

    @PostMapping("/{id}")
    public String updateTouristDestination(@PathVariable Long id, @ModelAttribute TouristDestinationDTO dto) {
        service.updateTouristDestination(id, dto);
        return "redirect:/tourist-destinations/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteTouristDestination(@PathVariable Long id) {
        service.deleteTouristDestination(id);
        return "redirect:/tourist-destinations";
    }
}
