package org.mydomain.shelterspringboot.controller;

import org.mydomain.shelterspringboot.model.Adopter;
import org.mydomain.shelterspringboot.model.AdoptionApplication;
import org.mydomain.shelterspringboot.service.AdoptionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
public class AdoptionController {

    private final AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping("/request")
    public ResponseEntity<String> processRequest(@RequestBody Adopter adopter,
                                                 @RequestParam Long dogId,
                                                 @RequestParam String notes) {
        try {
            adoptionService.processAdoptionRequest(adopter, dogId, notes);
            return ResponseEntity.status(HttpStatus.CREATED).body("Adoption request submitted successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{appId}/status")
    public ResponseEntity<String> changeStatus(@PathVariable Long appId,
                                               @RequestParam AdoptionApplication.ApplicationStatus status) {
        try {
            adoptionService.changeStatus(appId, status);
            return ResponseEntity.ok("Status updated to: " + status);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public List<AdoptionApplication> getAll() {
        return adoptionService.getAllApplications();
    }

    @GetMapping("/status/{status}")
    public List<AdoptionApplication> getByStatus(@PathVariable AdoptionApplication.ApplicationStatus status) {
        return adoptionService.getApplicationsByStatus(status);
    }

    @GetMapping("/dog/{dogId}")
    public List<AdoptionApplication> getByDog(@PathVariable Long dogId) {
        return adoptionService.getApplicationsForDog(dogId);
    }

    @GetMapping("/high-match")
    public List<AdoptionApplication> getHighMatch(@RequestParam(defaultValue = "70.0") double minMatch) {
        return adoptionService.getHighMatchApplications(minMatch);
    }

    @GetMapping("/range")
    public List<AdoptionApplication> getInRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return adoptionService.getApplicationsInRange(start, end);
    }
}