package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByNameContainingIgnoreCase(String name);
    List<Dog> findByBreedContainingIgnoreCase(String breed);
    List<Dog> findByIsAdoptedFalse();
    List<Dog> findByAgeBetween(int minAge, int maxAge);
    List<Dog> findByAgeLessThanEqual(int age);
    List<Dog> findByBreedAndIsAdoptedFalse(String breed);
}