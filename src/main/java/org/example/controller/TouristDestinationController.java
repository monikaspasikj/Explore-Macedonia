package org.example.controller;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.example.model.dto.TouristDestinationDTO;
import org.example.service.TouristDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
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
    public String rdfData() {
        return "tourist-destinations";
    }

    @GetMapping("/rdf")
    public ResponseEntity<String> getRdfData() {
        System.out.println("RDF data endpoint hit!");

        org.apache.jena.rdf.model.Model modelRDF = ModelFactory.createDefaultModel();
        String filePath = "src/main/java/org/example/tourist_destinations.ttl";
        FileManager.get().readModel(modelRDF, filePath, "TTL");

        StringWriter out = new StringWriter();
        modelRDF.write(out, "TURTLE");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/turtle"))
                .body(out.toString());
    }

//    @GetMapping
//    public String getAllTouristDestinations(Model model) {
//        List<TouristDestinationDTO> destinations = service.getAllTouristDestinations();
//        model.addAttribute("destinations", destinations);
//        return "destinations/list";
//    }

    @GetMapping
    public String getAllTouristDestinations(Model model) {
        List<TouristDestinationDTO> destinations = service.getAllTouristDestinations();
        model.addAttribute("destinations", destinations);
        return "tourist-destinations";
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
        return "tourist-destinations";
    }

    @GetMapping("/search/by-place-name")
    public String findByPlaceAndName(@RequestParam String place, @RequestParam String name, Model model) {
        List<TouristDestinationDTO> destinations = service.findByPlaceAndName(place, name);
        model.addAttribute("destinations", destinations);
        return "destinations/list";
    }

    @GetMapping("/search/by-location")
    public String findByLocation(@RequestParam String location, Model model) {
        List<TouristDestinationDTO> destinations = service.findByLocation(location);
        model.addAttribute("destinations", destinations);
        return "destinations/list";
    }
}
