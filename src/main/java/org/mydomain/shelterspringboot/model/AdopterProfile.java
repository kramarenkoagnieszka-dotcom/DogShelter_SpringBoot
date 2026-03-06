package org.mydomain.shelterspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class AdopterProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Energy level must be graded in 1-5 scale")
    @Max(value = 5, message = "Energy level must be graded in 1-5 scale")
    private int energyLevel;

    private boolean hasGarden;
    private boolean hasCats;
    private boolean hasDogs;
    private boolean hasKids;

    @Min(value = 50, message = "Minimum monthly budget for one dog is 50 EUR")
    private double monthlyBudget;

    private boolean willingForDisabledDog;

    private boolean hadDogBefore;
    private boolean hasBehavioralKnowledge;
    private boolean willingForBehavioralIssues;

    public AdopterProfile(int energyLevel, boolean hasGarden, boolean hasCats,
                          boolean hasDogs, boolean hasKids, double monthlyBudget,
                          boolean willingForDisabledDog, boolean hadDogBefore,
                          boolean hasBehavioralKnowledge, boolean willingForBehavioralIssues) {
        this.energyLevel = energyLevel;
        this.hasGarden = hasGarden;
        this.hasCats = hasCats;
        this.hasDogs = hasDogs;
        this.hasKids = hasKids;
        this.monthlyBudget = monthlyBudget;
        this.willingForDisabledDog = willingForDisabledDog;
        this.hadDogBefore = hadDogBefore;
        this.hasBehavioralKnowledge = hasBehavioralKnowledge;
        this.willingForBehavioralIssues = willingForBehavioralIssues;
    }

    protected AdopterProfile(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getEnergyLevel() { return energyLevel; }
    public void setEnergyLevel(int energyLevel) { this.energyLevel = energyLevel; }

    public boolean isHasGarden() { return hasGarden; }
    public void setHasGarden(boolean hasGarden) { this.hasGarden = hasGarden; }

    public boolean isHasCats() { return hasCats; }
    public void setHasCats(boolean hasCats) { this.hasCats = hasCats; }

    public boolean isHasDogs() { return hasDogs; }
    public void setHasDogs(boolean hasDogs) { this.hasDogs = hasDogs; }

    public boolean isHasKids() { return hasKids; }
    public void setHasKids(boolean hasKids) { this.hasKids = hasKids; }

    public double getMonthlyBudget() { return monthlyBudget; }
    public void setMonthlyBudget(double monthlyBudget) { this.monthlyBudget = monthlyBudget; }

    public boolean isWillingForDisabledDog() { return willingForDisabledDog; }
    public void setWillingForDisabledDog(boolean willingForDisabledDog) { this.willingForDisabledDog = willingForDisabledDog; }

    public boolean isHadDogBefore() { return hadDogBefore; }
    public void setHadDogBefore(boolean hadDogBefore) { this.hadDogBefore = hadDogBefore; }

    public boolean isHasBehavioralKnowledge() { return hasBehavioralKnowledge; }
    public void setHasBehavioralKnowledge(boolean hasBehavioralKnowledge) { this.hasBehavioralKnowledge = hasBehavioralKnowledge; }

    public boolean isWillingForBehavioralIssues() { return willingForBehavioralIssues; }
    public void setWillingForBehavioralIssues(boolean willingForBehavioralIssues) { this.willingForBehavioralIssues = willingForBehavioralIssues; }

    @Override
    public String toString() {
        return "AdopterProfile{" +
                "id=" + id +
                ", energyLevel=" + energyLevel +
                ", hasGarden=" + hasGarden +
                ", monthlyBudget=" + monthlyBudget +
                '}';
    }
}
