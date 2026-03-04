package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.*;
import org.mydomain.shelterspringboot.repository.DogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Shelter {

    private final DogRepository dogRepository;

    public Shelter(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Transactional
    public void createAndAddDog(String name, String breed, int age, DogProfile profile) {
        Dog newDog = new Dog(name, age, breed, profile);
        dogRepository.save(newDog);
    }

    @Transactional
    public void addDog(Dog dog) {
        dogRepository.save(dog);
    }

    public Optional<Dog> findDogById(Long dogId) {
        return dogRepository.findById(dogId);
    }

    public List<Dog> searchDogs(String query) {
        String lowerQuery = query.toLowerCase();

        List<Dog> byName = dogRepository.findByNameContainingIgnoreCase(lowerQuery);
        List<Dog> byBreed = dogRepository.findByBreedContainingIgnoreCase(lowerQuery);

        Optional<Dog> byId = Optional.empty();
        try {
            byId = dogRepository.findById(Long.parseLong(query));
        } catch (NumberFormatException ignored) {}

        return Stream.concat(
                Stream.concat(byName.stream(), byBreed.stream()),
                byId.stream()
        ).distinct().collect(Collectors.toList());
    }

    public List<Dog> getDogs() {
        return dogRepository.findAll();
    }

    public List<Dog> getDogsByAdvancedCriteria(String breed, Integer maxAge, Integer energy,
                                               Boolean garden, Boolean cats, Boolean dogs,
                                               Boolean kids, Boolean medical, Boolean behavioral) {
        return dogRepository.findDogsByAdvancedFilter(
                breed, maxAge, energy, garden, cats, dogs, kids, medical, behavioral
        );
    }
}