package org.example.controller;

import org.example.model.dto.CulturalHeritageDTO;
import org.example.service.CulturalHeritageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.example.model.dto.CulturalHeritageDTO;
import org.example.service.CulturalHeritageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cultural-heritages")
public class CulturalHeritageController {

    private final CulturalHeritageService service;

    @Autowired
    public CulturalHeritageController(CulturalHeritageService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllCulturalHeritages(Model model) {
        List<CulturalHeritageDTO> culturalHeritages = service.getAllCulturalHeritages();
        model.addAttribute("culturalHeritages", culturalHeritages);
        return "cultural-heritages/list";
    }

    @GetMapping("/{id}")
    public String getCulturalHeritageById(@PathVariable Long id, Model model) {
        CulturalHeritageDTO culturalHeritage = service.getCulturalHeritageById(id);
        model.addAttribute("culturalHeritage", culturalHeritage);
        return "cultural-heritages/detail";
    }

    @GetMapping("/search/by-name")
    public String findByName(@RequestParam String name, Model model) {
        List<CulturalHeritageDTO> culturalHeritages = service.findByName(name);
        model.addAttribute("culturalHeritages", culturalHeritages);
        return "cultural-heritages/list";
    }

    @GetMapping("/search/by-location")
    public String findByLocation(@RequestParam String location, Model model) {
        List<CulturalHeritageDTO> culturalHeritages = service.findByLocation(location);
        model.addAttribute("culturalHeritages", culturalHeritages);
        return "cultural-heritages/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("culturalHeritage", new CulturalHeritageDTO());
        return "cultural-heritages/create";
    }

    @PostMapping
    public String createCulturalHeritage(@ModelAttribute CulturalHeritageDTO dto, Model model) {
        CulturalHeritageDTO createdCulturalHeritage = service.createCulturalHeritage(dto);
        model.addAttribute("culturalHeritage", createdCulturalHeritage);
        return "redirect:/cultural-heritages/" + createdCulturalHeritage.getId();
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        CulturalHeritageDTO culturalHeritage = service.getCulturalHeritageById(id);
        model.addAttribute("culturalHeritage", culturalHeritage);
        return "cultural-heritages/edit";
    }

    @PostMapping("/{id}")
    public String updateCulturalHeritage(@PathVariable Long id, @ModelAttribute CulturalHeritageDTO dto) {
        service.updateCulturalHeritage(id, dto);
        return "redirect:/cultural-heritages/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteCulturalHeritage(@PathVariable Long id) {
        service.deleteCulturalHeritage(id);
        return "redirect:/cultural-heritages";
    }
}

