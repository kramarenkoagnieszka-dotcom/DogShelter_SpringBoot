package org.mydomain.shelterspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class DogProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Energy level must be graded in 1-5 scale")
    @Max(value = 5, message = "Energy level must be graded in 1-5 scale")
    private int energyLevel = 1;

    private boolean needsGarden;
    private boolean goodWithCats;
    private boolean goodWithDogs;
    private boolean goodWithKids;
    private boolean specialMedicalNeeds;
    private boolean specialBehavioralNeeds;

    public DogProfile(int energyLevel, boolean needsGarden, boolean goodWithCats,
                      boolean goodWithDogs, boolean goodWithKids,
                      boolean specialMedicalNeeds, boolean specialBehavioralNeeds) {
        this.energyLevel = energyLevel;
        this.needsGarden = needsGarden;
        this.goodWithCats = goodWithCats;
        this.goodWithDogs = goodWithDogs;
        this.goodWithKids = goodWithKids;
        this.specialMedicalNeeds = specialMedicalNeeds;
        this.specialBehavioralNeeds = specialBehavioralNeeds;
    }

    public DogProfile(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getEnergyLevel() { return energyLevel; }
    public void setEnergyLevel(int energyLevel) { this.energyLevel = energyLevel; }

    public boolean isNeedsGarden() { return needsGarden; }
    public void setNeedsGarden(boolean needsGarden) { this.needsGarden = needsGarden; }

    public boolean isGoodWithCats() { return goodWithCats; }
    public void setGoodWithCats(boolean goodWithCats) { this.goodWithCats = goodWithCats; }

    public boolean isGoodWithDogs() { return goodWithDogs; }
    public void setGoodWithDogs(boolean goodWithDogs) { this.goodWithDogs = goodWithDogs; }

    public boolean isGoodWithKids() { return goodWithKids; }
    public void setGoodWithKids(boolean goodWithKids) { this.goodWithKids = goodWithKids; }

    public boolean isSpecialMedicalNeeds() { return specialMedicalNeeds; }
    public void setSpecialMedicalNeeds(boolean specialMedicalNeeds) { this.specialMedicalNeeds = specialMedicalNeeds; }

    public boolean isSpecialBehavioralNeeds() { return specialBehavioralNeeds; }
    public void setSpecialBehavioralNeeds(boolean specialBehavioralNeeds) { this.specialBehavioralNeeds = specialBehavioralNeeds; }

    @Override
    public String toString() {
        return "DogProfile{" +
                "id=" + id +
                ", energyLevel=" + energyLevel +
                ", needsGarden=" + needsGarden +
                ", goodWithCats=" + goodWithCats +
                ", goodWithDogs=" + goodWithDogs +
                ", goodWithKids=" + goodWithKids +
                '}';
    }
}