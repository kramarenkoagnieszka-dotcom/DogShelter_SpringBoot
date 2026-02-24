package org.mydomain.shelterspringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
@Entity
@DiscriminatorValue("expense")
public class Expense extends FinancialTransaction {
    private String description;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Expense( double amount, LocalDate date, String description, Dog dog, Staff staff) {
        super( amount, date);
        this.description = description;
        this.dog = dog;
        this.staff = staff;
    }

    protected Expense() {}

    public String getDescription() { return description; }
    public Dog getDog() { return dog; }
    public Staff getStaff() { return staff; }


    public void setDescription(String description) { this.description = description; }
    public void setDog(Dog dog) { this.dog = dog; }
    public void setStaff(Staff staff) { this.staff = staff; }

    @Override
    public String toString() {
        return String.format("Expense ID: %s | Amount: %.2f PLN | Note: %s | Dog: %s | Staff: %s",
                getId(),
                getAmount(),
                description,
                (dog != null ? dog.getName() : "None"),
                (staff != null ? staff.getUsername() : "None"));
    }
}
