package org.mydomain.shelterspringboot.model;

import java.time.LocalDate;

public class Donation extends FinancialTransaction {
    private Donor donor;

    public Donation(Long id, double amount, LocalDate date, Donor donor) {
        super(id, amount, date);
        this.donor = donor;
    }

    protected Donation() {}

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    @Override
    public String toString() {
        return String.format("Donation: %s - %.2f PLN (Donor: %s)",
                this.getDate(),
                this.getAmount(),
                (donor != null ? donor.getUsername() : "Unknown"));
    }
}