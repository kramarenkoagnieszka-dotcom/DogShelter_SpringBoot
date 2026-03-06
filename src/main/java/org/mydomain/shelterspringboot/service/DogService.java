package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.*;
import org.mydomain.shelterspringboot.repository.DogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Transactional
    public void addDog(Dog dog) {
        dogRepository.save(dog);
    }

    @Transactional
    public void createAndAddDog(String name, String breed, int age, DogProfile profile) {
        Dog newDog = new Dog(name, age, breed, profile);
        dogRepository.save(newDog);
    }
    @Transactional
    public void createAndAddDog(String name, String breed, int age) {
        Dog newDog = new Dog(name, age, breed, new DogProfile());
        dogRepository.save(newDog);
    }

    @Transactional
    public void addProfileToDog(Long dogId, DogProfile profile) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new IllegalArgumentException("Dog with ID " + dogId + " not found"));
        dog.setProfile(profile);
        dogRepository.save(dog);
    }

    public Optional<Dog> findDogById(Long dogId) {
        return dogRepository.findById(dogId);
    }

    public List<Dog> getDogsAvailableForAdoption() {
        return dogRepository.findByIsAdoptedFalse();
    }

    public List<Dog> getDogsByAgeRange(int minAge, int maxAge) {
        return dogRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Dog> getDogsYoungerThan(int age) {
        return dogRepository.findByAgeLessThanEqual(age);
    }

    public List<Dog> getAvailableDogsByBreed(String breed) {
        return dogRepository.findByBreedAndIsAdoptedFalse(breed);
    }

    public List<Dog> searchByName(String name) {
        return dogRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Dog> searchByBreed(String breed) {
        return dogRepository.findByBreedContainingIgnoreCase(breed);
    }

    public List<Dog> getAllDogs() {
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