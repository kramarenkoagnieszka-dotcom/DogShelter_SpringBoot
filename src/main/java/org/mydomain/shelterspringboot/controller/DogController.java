package org.mydomain.shelterspringboot.controller;

import org.mydomain.shelterspringboot.model.Dog;
import org.mydomain.shelterspringboot.service.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDog(@RequestBody Dog dog) {
        dogService.addDog(dog);
    }

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        return dogService.findDogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public List<Dog> getAvailableDogs() {
        return dogService.getDogsAvailableForAdoption();
    }

    @GetMapping("/search/name")
    public List<Dog> searchByName(@RequestParam String name) {
        return dogService.searchByName(name);
    }

    @GetMapping("/search/breed")
    public List<Dog> searchByBreed(@RequestParam String breed) {
        return dogService.searchByBreed(breed);
    }

    @GetMapping("/filter")
    public List<Dog> filterDogs(
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Integer energy,
            @RequestParam(required = false) Boolean garden,
            @RequestParam(required = false) Boolean cats,
            @RequestParam(required = false) Boolean dogs,
            @RequestParam(required = false) Boolean kids,
            @RequestParam(required = false) Boolean medical,
            @RequestParam(required = false) Boolean behavioral) {
        return dogService.getDogsByAdvancedCriteria(
                breed, maxAge, energy, garden, cats, dogs, kids, medical, behavioral
        );
    }
}