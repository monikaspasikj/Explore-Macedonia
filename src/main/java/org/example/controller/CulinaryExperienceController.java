package org.example.controller;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.example.model.dto.CulinaryExperienceDTO;
import org.example.service.CulinaryExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;

@Controller
@RequestMapping("/culinary-experiences")
public class CulinaryExperienceController {

    private final CulinaryExperienceService service;

    @Autowired
    public CulinaryExperienceController(CulinaryExperienceService service) {
        this.service = service;
    }

    @GetMapping
    public String rdfData() {
        return "culinaryexperience";
    }

    @GetMapping("/rdf")
    public ResponseEntity<String> getRdfData() {
        System.out.println("RDF data endpoint hit!");

        org.apache.jena.rdf.model.Model modelRDF = ModelFactory.createDefaultModel();
        String filePath = "src/main/java/org/example/culinary_experiences.ttl";
        FileManager.get().readModel(modelRDF, filePath, "TTL");

        StringWriter out = new StringWriter();
        modelRDF.write(out, "TURTLE");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/turtle"))
                .body(out.toString());
    }

//    @GetMapping
//    public String getAllCulinaryExperiences(Model model) {
//        List<CulinaryExperienceDTO> experiences = service.getAllCulinaryExperiences();
//        model.addAttribute("experiences", experiences);
//        return "culinary-experiences/list";
//    }

    @GetMapping("/{id}")
    public String getCulinaryExperienceById(@PathVariable Long id, Model model) {
        CulinaryExperienceDTO experience = service.getCulinaryExperienceById(id);
        model.addAttribute("experience", experience);
        return "culinary-experiences/detail";
    }

    @GetMapping("/search/by-name")
    public String findByName(@RequestParam String name, Model model) {
        List<CulinaryExperienceDTO> experiences = service.findByName(name);
        model.addAttribute("experiences", experiences);
        return "culinaryexperience";
    }

    @GetMapping("/search/by-location")
    public String findByLocation(@RequestParam String location, Model model) {
        List<CulinaryExperienceDTO> experiences = service.findByLocation(location);
        model.addAttribute("experiences", experiences);
        return "culinary-experiences/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("culinaryExperience", new CulinaryExperienceDTO());
        return "culinary-experiences/create";
    }

    @PostMapping
    public String createCulinaryExperience(@ModelAttribute CulinaryExperienceDTO dto, Model model) {
        CulinaryExperienceDTO createdExperience = service.createCulinaryExperience(dto);
        model.addAttribute("experience", createdExperience);
        return "redirect:/culinary-experiences/" + createdExperience.getId(); // Редиректирање на деталите за ново создаденото искуство
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        CulinaryExperienceDTO experience = service.getCulinaryExperienceById(id);
        model.addAttribute("culinaryExperience", experience);
        return "culinary-experiences/edit";
    }

    @PostMapping("/{id}")
    public String updateCulinaryExperience(@PathVariable Long id, @ModelAttribute CulinaryExperienceDTO dto) {
        service.updateCulinaryExperience(id, dto);
        return "redirect:/culinary-experiences/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteCulinaryExperience(@PathVariable Long id) {
        service.deleteCulinaryExperience(id);
        return "redirect:/culinary-experiences";
    }
}
