package org.mydomain.shelterspringboot.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "transactionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Expense.class, name = "expense"),
        @JsonSubTypes.Type(value = Donation.class, name = "donation")
})
public abstract class FinancialTransaction {
    private Long id;
    private double amount;
    private LocalDate date;

    public FinancialTransaction(Long id, double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    protected FinancialTransaction(){}

    public Long getId() { return id; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    public void setId(Long id) { this.id = id; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
}