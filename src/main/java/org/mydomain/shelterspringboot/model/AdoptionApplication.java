package org.mydomain.shelterspringboot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Entity
public class AdoptionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @JoinColumn(name = "dog_id")
    @ManyToOne
    private Adopter adopter;
    private LocalDateTime applicationDate;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private double matchPercentage;
    private String notes;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AdoptionApplication(Dog dog, Adopter adopter, double matchPercentage, String notes) {
        this.dog = dog;
        this.adopter = adopter;
        this.matchPercentage = matchPercentage;
        this.applicationDate = LocalDateTime.now();
        this.status = ApplicationStatus.PENDING;
        this.notes = notes;
    }

    protected AdoptionApplication(){}

    public enum ApplicationStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Long getId() { return id; }
    public Dog getDog() { return dog; }
    public Adopter getAdopter() { return adopter; }
    public LocalDateTime getApplicationDate() { return applicationDate; }
    public ApplicationStatus getStatus() { return status; }
    public double getMatchPercentage() { return matchPercentage; }
    public String getNotes() { return notes; }

    public void setId(Long id) { this.id = id; }
    public void setDog(Dog dog) { this.dog = dog; }
    public void setAdopter(Adopter adopter) { this.adopter = adopter; }
    public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public void setMatchPercentage(double matchPercentage) { this.matchPercentage = matchPercentage; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isProcessed() {
        return this.status != ApplicationStatus.PENDING;
    }

    @Override
    public String toString() {
        return "AdoptionApplication{" +
                "id=" + id +
                ", status=" + status +
                ", dog=" + (dog != null ? dog.getName() : "null") +
                ", match=" + String.format("%.2f%%", matchPercentage) +
                '}';
    }
}
