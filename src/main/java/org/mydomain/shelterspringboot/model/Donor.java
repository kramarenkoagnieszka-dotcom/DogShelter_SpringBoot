package org.mydomain.shelterspringboot.model;

import java.util.ArrayList;
import java.util.List;

public class Donor extends User {
    private List<Donation> donationHistory;

    public Donor(Long id, String firstName, String lastName, String username, String password, String email) {
        super(id, firstName, lastName, username, password, email);
        this.donationHistory = new ArrayList<>();
    }

    protected Donor() {
        super();
    }

    public List<Donation> getDonationHistory() {
        return donationHistory;
    }

    public void setDonationHistory(List<Donation> donationHistory) {
        this.donationHistory = donationHistory;
    }

    public void addDonation(Donation donation) {
        if (this.donationHistory == null) {
            this.donationHistory = new ArrayList<>();
        }
        this.donationHistory.add(donation);
    }
}
