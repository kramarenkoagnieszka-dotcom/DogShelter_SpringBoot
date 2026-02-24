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
    @JoinColumn(name = "user_id") // Zmieniono z donor_id na user_id dla jasności
    private User user;

    public Donation(double amount, LocalDate date, User user) {
        super(amount, date);
        this.user = user;
    }

    protected Donation() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Donation: %s - %.2f PLN (User: %s)",
                this.getDate(),
                this.getAmount(),
                (user != null ? user.getUsername() : "Unknown"));
    }
}