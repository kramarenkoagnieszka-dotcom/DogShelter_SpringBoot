package org.mydomain.shelterspringboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@DiscriminatorValue("donor")
public class Donor extends User {
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donationHistory = new ArrayList<>();

    public Donor(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
        this.donationHistory = new ArrayList<>();
    }

    protected Donor() {
        super();
    }

    public List<Donation> getDonationHistory() {
        return Collections.unmodifiableList(donationHistory);
    }

    public void setDonationHistory(List<Donation> donationHistory) {
        this.donationHistory = donationHistory;
    }

    public void addDonation(Donation donation) {
        this.donationHistory.add(donation);
        donation.setDonor(this);
    }
}
