package org.mydomain.shelterspringboot.model;

import java.time.LocalDate;

public class Expense extends FinancialTransaction {
    private String description;
    private Dog dog;
    private Long staffId;

    public Expense(Long id, double amount, LocalDate date, String description, Dog dog, Long staffId) {
        super(id, amount, date);
        this.description = description;
        this.dog = dog;
        this.staffId = staffId;
    }

    protected Expense() {} // Konstruktor dla mechanizmów Springa

    public String getDescription() { return description; }
    public Dog getDog() { return dog; }
    public Long getStaffId() { return staffId; }


    public void setDescription(String description) { this.description = description; }
    public void setDog(Dog dog) { this.dog = dog; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    @Override
    public String toString() {
        return String.format("Expense ID: %d | Amount: %.2f PLN | Note: %s | Dog: %s",
                getId(),
                getAmount(),
                description,
                (dog != null ? dog.getName() : "None"));
    }
}
