package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Shelter {
    private List<Dog> dogs;
    private Long dogSequence; // Licznik sekwencji ID

    public void createAndAddDog(String name, String breed, int age, DogProfile profile) {
        dogSequence++;
        Dog newDog = new Dog(dogSequence, name, age, breed, profile);
        dogs.add(newDog);
    }

    public void addDog(Dog dog) {
        if (findDogById(dog.getId()).isPresent()) {
            throw new IllegalArgumentException("Dog with ID " + dog.getId() + " already exists in the shelter.");
        }
        dogs.add(dog);
    }

    public void removeDogAfterAdoption(Long dogId) {
        boolean removed = dogs.removeIf(d -> d.getId().equals(dogId));
        if (!removed) {
            throw new IllegalArgumentException("Cannot remove dog: Dog with ID " + dogId + " not found.");
        }
    }

    public Optional<Dog> findDogById(Long dogId) {
        return dogs.stream()
                .filter(dog -> dog.getId().equals(dogId))
                .findFirst();
    }

    public List<Dog> searchDogs(String query) {
        List<Dog> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        try {
            Long id = Long.parseLong(query);
            findDogById(id).ifPresent(results::add);
        } catch (NumberFormatException ignored) {
        }

        dogs.stream()
                .filter(d -> d.getName().toLowerCase().contains(lowerQuery) ||
                        d.getBreed().toLowerCase().contains(lowerQuery))
                .filter(d -> results.stream().noneMatch(r -> r.getId() == d.getId()))
                .forEach(results::add);

        return results;
    }

    public List<Dog> getDogs() {
        return new ArrayList<>(dogs);
    }
}