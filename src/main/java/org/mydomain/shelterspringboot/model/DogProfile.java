package org.mydomain.shelterspringboot.model;

public class DogProfile {
    private Long id;
    private int energyLevel;
    private boolean needsGarden;
    private boolean goodWithCats;
    private boolean goodWithDogs;
    private boolean goodWithKids;
    private boolean specialMedicalNeeds;
    private boolean specialBehavioralNeeds;

    public DogProfile(Long id, int energyLevel, boolean needsGarden, boolean goodWithCats,
                      boolean goodWithDogs, boolean goodWithKids,
                      boolean specialMedicalNeeds, boolean specialBehavioralNeeds) {
        this.id = id;
        this.energyLevel = energyLevel;
        this.needsGarden = needsGarden;
        this.goodWithCats = goodWithCats;
        this.goodWithDogs = goodWithDogs;
        this.goodWithKids = goodWithKids;
        this.specialMedicalNeeds = specialMedicalNeeds;
        this.specialBehavioralNeeds = specialBehavioralNeeds;
    }

    protected DogProfile(){}

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