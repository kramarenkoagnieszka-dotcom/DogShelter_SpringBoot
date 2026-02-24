package org.mydomain.shelterspringboot.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@DiscriminatorValue("adopter")
public class Adopter extends User implements Donatable {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private AdopterProfile profile;

    @OneToMany(mappedBy = "adopter", cascade = CascadeType.ALL)
    private List<Donation> donationHistory = new ArrayList<>();

    public Adopter(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
        this.donationHistory = new ArrayList<>();
    }

    public Adopter(String firstName, String lastName, String username, String password, String email, AdopterProfile profile) {
        super(firstName, lastName, username, password, email);
        this.profile = profile;
        this.donationHistory = new ArrayList<>();
    }

    protected Adopter() {
        super();
    }

    public AdopterProfile getProfile() { return profile; }
    public List<Donation> getDonationHistory() {return Collections.unmodifiableList(donationHistory);}

    public void setProfile(AdopterProfile profile) { this.profile = profile; }


    public void addDonation(Donation donation) {
        this.donationHistory.add(donation);
        donation.setUser(this);
    }
}