package org.mydomain.shelterspringboot.repository;

import org.mydomain.shelterspringboot.model.AdoptionApplication;
import org.mydomain.shelterspringboot.model.AdoptionApplication.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {

    List<AdoptionApplication> findByStatus(ApplicationStatus status);
    List<AdoptionApplication> findByDogId(Long dogId);
    List<AdoptionApplication> findByAdopterId(Long adopterId);

    List<AdoptionApplication> findByMatchPercentageGreaterThanEqual(double percentage);
    List<AdoptionApplication> findByMatchPercentageLessThanEqual(double percentage);

    List<AdoptionApplication> findByApplicationDateBetween(LocalDateTime start, LocalDateTime end);
    List<AdoptionApplication> findByApplicationDateAfter(LocalDateTime date);
    List<AdoptionApplication> findByApplicationDateBefore(LocalDateTime date);
}