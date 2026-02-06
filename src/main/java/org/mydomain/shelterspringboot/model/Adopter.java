package org.mydomain.shelterspringboot.model;

import java.util.ArrayList;
import java.util.List;

public class Adopter extends User {
    private AdopterProfile profile;
    private List<Donation> donationHistory;

    public Adopter(Long id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email);
        this.donationHistory = new ArrayList<>();
    }

    public Adopter(Long id, String firstName, String lastName, String username, String password, String email, AdopterProfile profile) {
        super(id, firstName, lastName, username, password, email);
        this.profile = profile;
        this.donationHistory = new ArrayList<>();
    }

    protected Adopter() {
        super();
    }

    public AdopterProfile getProfile() { return profile; }
    public List<Donation> getDonationHistory() { return donationHistory; }

    public void setProfile(AdopterProfile profile) { this.profile = profile; }
    public void setDonationHistory(List<Donation> donationHistory) { this.donationHistory = donationHistory; }

    public void addDonation(Donation donation) {
        if (this.donationHistory == null) {
            this.donationHistory = new ArrayList<>();
        }
        this.donationHistory.add(donation);
    }
}