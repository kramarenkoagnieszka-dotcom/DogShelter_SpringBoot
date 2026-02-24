package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialService {
    private List<Expense> expenses = new ArrayList<>();
    private List<Donation> donations = new ArrayList<>();
    private double balance = 0.0;
    private final Shelter shelter;
    private final UserService userService;


    public FinancialService(Shelter shelter, UserService userService) {
        this.shelter = shelter;
        this.userService = userService;
    }


    public void registerExpense(Staff staff, long dogId, double amount, String description) {
        Dog dog = shelter.findDogById(dogId)
                .orElseThrow(() -> new IllegalArgumentException("Dog with ID " + dogId + " not found."));

        LocalDate today = LocalDate.now();

        Expense newExpense = new Expense(amount, today, description, dog, staff);

        addExpense(newExpense);
    }

    public void registerDonation(Donor donor, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Donation amount must be greater than 0.");
        }

        LocalDate today = LocalDate.now();

        Donation newDonation = new Donation(amount, today, donor);

        addDonation(newDonation);
    }

    public void addExpense(Expense expense) {
        if (expense.getAmount() > balance) {
            throw new IllegalArgumentException("Insufficient funds. Current balance: " + balance + " PLN.");
        }

        expenses.add(expense);
        balance -= expense.getAmount();

        shelter.findDogById(expense.getDog().getId()).ifPresent(dog -> {
            dog.addExpense(expense);
        });
    }

    public void addDonation(Donation donation) {
        User user = donation.getUser();
        if (user == null) throw new IllegalArgumentException("Donation must have a user.");

        userService.findUserById(user.getId()).ifPresentOrElse(foundUser -> {
            if (foundUser instanceof Donatable donatable) {
                donations.add(donation);
                balance += donation.getAmount();
                donatable.addDonation(donation);
            } else {
                throw new IllegalArgumentException("This user type cannot make donations.");
            }
        }, () -> {
            throw new IllegalArgumentException("User not found.");
        });
    }

    public double getBalance() {
        return balance;
    }

    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

    public List<Donation> getAllDonations() {
        return new ArrayList<>(donations);
    }

    public double calculateTotalDonatedBy(Donor donor) {
        return donor.getDonationHistory().stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }

    public List<Expense> getExpensesByStaff(Long staffId) {
        return expenses.stream()
                .filter(e -> e.getStaff() != null && e.getStaff().getId().equals(staffId))
                .collect(Collectors.toList());
    }
}
