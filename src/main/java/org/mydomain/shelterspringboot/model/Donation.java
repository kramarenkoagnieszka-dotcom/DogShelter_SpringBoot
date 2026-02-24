package org.mydomain.shelterspringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
@Entity
@DiscriminatorValue("donation")
public class Donation extends FinancialTransaction {
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    public Donation( double amount, LocalDate date, Donor donor) {
        super(amount, date);
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