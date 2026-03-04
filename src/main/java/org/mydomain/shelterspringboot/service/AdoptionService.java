package org.mydomain.shelterspringboot.service;

import jakarta.transaction.Transactional;
import org.mydomain.shelterspringboot.model.Adopter;
import org.mydomain.shelterspringboot.model.AdoptionApplication;
import org.mydomain.shelterspringboot.model.Dog;
import org.mydomain.shelterspringboot.repository.AdoptionApplicationRepository;
import org.mydomain.shelterspringboot.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdoptionService {

    private final AdoptionApplicationRepository applicationRepository;
    private final DogRepository dogRepository;
    private final MatchingService matchingService;

    public AdoptionService(AdoptionApplicationRepository applicationRepository,
                           DogRepository dogRepository,
                           MatchingService matchingService) {
        this.applicationRepository = applicationRepository;
        this.dogRepository = dogRepository;
        this.matchingService = matchingService;
    }

    @Transactional
    public void processAdoptionRequest(Adopter adopter, Long dogId, String notes) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new IllegalArgumentException("Dog not found."));

        double match = matchingService.calculateMatchPercentage(dog, adopter);
        if (match < 50.0) {
            throw new IllegalStateException("Match too low: " + match + "%. Required: 50%.");
        }

        AdoptionApplication app = new AdoptionApplication(dog, adopter, match, notes);
        applicationRepository.save(app);
    }

    @Transactional
    public void changeStatus(Long appId, AdoptionApplication.ApplicationStatus newStatus) {
        AdoptionApplication app = applicationRepository.findById(appId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found."));

        app.setStatus(newStatus);

        if (newStatus == AdoptionApplication.ApplicationStatus.ACCEPTED) {
            Dog dog = app.getDog();
            dog.setAdopted(true);
            dogRepository.save(dog);
        }
    }


    public List<AdoptionApplication> getApplicationsByStatus(AdoptionApplication.ApplicationStatus status) {
        return applicationRepository.findByStatus(status);
    }

    public List<AdoptionApplication> getApplicationsForDog(Long dogId) {
        return applicationRepository.findByDogId(dogId);
    }

    public List<AdoptionApplication> getApplicationsByAdopter(Long adopterId) {
        return applicationRepository.findByAdopterId(adopterId);
    }

    public List<AdoptionApplication> getHighMatchApplications(double minPercentage) {
        return applicationRepository.findByMatchPercentageGreaterThanEqual(minPercentage);
    }

    public List<AdoptionApplication> getApplicationsInRange(LocalDateTime start, LocalDateTime end) {
        return applicationRepository.findByApplicationDateBetween(start, end);
    }

    public List<AdoptionApplication> getRecentApplications(LocalDateTime since) {
        return applicationRepository.findByApplicationDateAfter(since);
    }

    public List<AdoptionApplication> getAllApplications() {
        return applicationRepository.findAll();
    }
}