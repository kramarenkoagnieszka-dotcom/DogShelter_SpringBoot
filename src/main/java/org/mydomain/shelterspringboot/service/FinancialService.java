package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.*;
import org.mydomain.shelterspringboot.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialService {

    private final DonationRepository donationRepository;
    private final ExpenseRepository expenseRepository;
    private final FinancialTransactionRepository transactionRepository;
    private final DogRepository dogRepository;

    public FinancialService(DonationRepository donationRepository,
                            ExpenseRepository expenseRepository,
                            FinancialTransactionRepository transactionRepository,
                            DogRepository dogRepository) {
        this.donationRepository = donationRepository;
        this.expenseRepository = expenseRepository;
        this.transactionRepository = transactionRepository;
        this.dogRepository = dogRepository;
    }

    public double getBalance() {
        double totalDonations = donationRepository.findAll().stream()
                .mapToDouble(Donation::getAmount)
                .sum();
        double totalExpenses = expenseRepository.findAll().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        return totalDonations - totalExpenses;
    }

    @Transactional
    public void registerExpense(Staff staff, Long dogId, double amount, String description) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new IllegalArgumentException("Dog with ID " + dogId + " not found."));

        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds. Current balance: " + getBalance() + " PLN.");
        }

        Expense newExpense = new Expense(amount, LocalDate.now(), description, dog, staff);
        expenseRepository.save(newExpense);
    }

    @Transactional
    public void registerDonation(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Donation amount must be greater than 0.");
        }

        if (!(user instanceof Donatable)) {
            throw new IllegalArgumentException("This user type cannot make donations.");
        }

        Donation newDonation = new Donation(amount, LocalDate.now(), user);
        donationRepository.save(newDonation);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public List<Expense> getExpensesByStaff(Staff staff) {
        return expenseRepository.findByStaff(staff);
    }

    public double calculateTotalDonatedBy(User user) {
        return donationRepository.findByUser(user).stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }

    public List<FinancialTransaction> getRecentTransactions() {
        return transactionRepository.findTop10ByOrderByDateDesc();
    }
}
