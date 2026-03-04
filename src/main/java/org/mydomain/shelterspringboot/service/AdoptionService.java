package org.mydomain.shelterspringboot.service;

import jakarta.transaction.Transactional;
import org.mydomain.shelterspringboot.model.Adopter;
import org.mydomain.shelterspringboot.model.AdoptionApplication;
import org.mydomain.shelterspringboot.model.Dog;
import org.mydomain.shelterspringboot.repository.AdoptionApplicationRepository;
import org.mydomain.shelterspringboot.repository.DogRepository;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {

    private final AdoptionApplicationRepository applicationRepository;
    private final DogRepository dogRepository;
    private final MatchingService matchingService;

    public AdoptionService(AdoptionApplicationRepository appRepo,
                           DogRepository dogRepo,
                           MatchingService matchingService) {
        this.applicationRepository = appRepo;
        this.dogRepository = dogRepo;
        this.matchingService = matchingService;
    }

    @Transactional
    public void processAdoptionRequest(Adopter adopter, Long dogId, String notes) {
        Dog dog = dogRepository.findById(dogId).orElseThrow();

        double match = matchingService.calculateMatchPercentage(dog, adopter);
        if (match < 50.0) throw new IllegalStateException("Match too low");

        AdoptionApplication app = new AdoptionApplication(dog, adopter, match, notes);
        applicationRepository.save(app);
    }

    @Transactional
    public void changeStatus(Long appId, AdoptionApplication.ApplicationStatus newStatus) {
        AdoptionApplication app = applicationRepository.findById(appId).orElseThrow();
        app.setStatus(newStatus);

        if (newStatus == AdoptionApplication.ApplicationStatus.ACCEPTED) {
            Dog dog = app.getDog();
            dog.setAdopted(true);
            dogRepository.save(dog);
        }
    }
}