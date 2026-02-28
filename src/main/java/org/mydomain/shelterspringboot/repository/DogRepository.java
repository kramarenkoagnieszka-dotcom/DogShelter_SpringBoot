package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT d FROM Dog d JOIN d.profile p WHERE " +
            "(:breed IS NULL OR LOWER(d.breed) LIKE LOWER(CONCAT('%', :breed, '%'))) AND " +
            "(:maxAge IS NULL OR d.age <= :maxAge) AND " +
            "(:energy IS NULL OR p.energyLevel <= :energy) AND " +
            "(:garden IS NULL OR p.needsGarden = :garden) AND " +
            "(:cats IS NULL OR p.goodWithCats = :cats) AND " +
            "(:dogs IS NULL OR p.goodWithDogs = :dogs) AND " +
            "(:kids IS NULL OR p.goodWithKids = :kids) AND " +
            "(:medical IS NULL OR p.specialMedicalNeeds = :medical) AND " +
            "(:behavioral IS NULL OR p.specialBehavioralNeeds = :behavioral) AND " +
            "(d.isAdopted = false)")
    List<Dog> findDogsByAdvancedFilter(
            @Param("breed") String breed,
            @Param("maxAge") Integer maxAge,
            @Param("energy") Integer energy,
            @Param("garden") Boolean garden,
            @Param("cats") Boolean cats,
            @Param("dogs") Boolean dogs,
            @Param("kids") Boolean kids,
            @Param("medical") Boolean medical,
            @Param("behavioral") Boolean behavioral
    );
}