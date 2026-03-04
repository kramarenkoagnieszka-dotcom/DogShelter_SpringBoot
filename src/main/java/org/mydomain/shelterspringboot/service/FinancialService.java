package org.mydomain.shelterspringboot.service;

import jakarta.transaction.Transactional;
import org.mydomain.shelterspringboot.model.*;
import org.mydomain.shelterspringboot.repository.DogRepository;
import org.mydomain.shelterspringboot.repository.DonationRepository;
import org.mydomain.shelterspringboot.repository.ExpenseRepository;
import org.mydomain.shelterspringboot.repository.FinancialTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialService {

    private final FinancialTransactionRepository transactionRepository;
    private final ExpenseRepository expenseRepository;
    private final DonationRepository donationRepository;
    private final DogRepository dogRepository;

    public FinancialService(FinancialTransactionRepository transactionRepository,
                            ExpenseRepository expenseRepository,
                            DonationRepository donationRepository,
                            DogRepository dogRepository) {
        this.transactionRepository = transactionRepository;
        this.expenseRepository = expenseRepository;
        this.donationRepository = donationRepository;
        this.dogRepository = dogRepository;
    }

    public double getBalance() {
        Double donations = transactionRepository.sumAllDonations();
        Double expenses = transactionRepository.sumAllExpenses();
        return (donations != null ? donations : 0.0) - (expenses != null ? expenses : 0.0);
    }

    @Transactional
    public void registerExpense(Staff staff, Long dogId, double amount, String description) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new IllegalArgumentException("Dog not found."));

        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        transactionRepository.save(new Expense(amount, LocalDate.now(), description, dog, staff));
    }

    @Transactional
    public void registerDonation(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Donation amount must be greater than 0.");
        }
        if (!(user instanceof Donatable)) {
            throw new IllegalArgumentException("This user type cannot make donations.");
        }

        transactionRepository.save(new Donation(amount, LocalDate.now(), user));
    }

    public List<FinancialTransaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findByDate(date);
    }

    public List<FinancialTransaction> getTransactionsInRange(LocalDate start, LocalDate end) {
        return transactionRepository.findByDateBetween(start, end);
    }

    public List<FinancialTransaction> getLargeTransactions(double minAmount) {
        return transactionRepository.findByAmountGreaterThanEqual(minAmount);
    }

    public List<FinancialTransaction> getRecentTransactions() {
        return transactionRepository.findTop10ByOrderByDateDesc();
    }
    public List<Expense> getExpensesByDog(Dog dog) {
        return expenseRepository.findByDog(dog);
    }

    public List<Expense> getExpensesByStaff(Staff staff) {
        return expenseRepository.findByStaff(staff);
    }

    public List<Expense> searchExpensesByDescription(String keyword) {
        return expenseRepository.findByDescriptionContainingIgnoreCase(keyword);
    }
    public List<Donation> getDonationsByUser(User user) {
        return donationRepository.findByUser(user);
    }

    public List<Donation> getDonationHistoryByUser(User user) {
        return donationRepository.findByUserOrderByDateDesc(user);
    }

    public List<Donation> getLargeDonations(double minAmount) {
        return donationRepository.findByAmountGreaterThanEqual(minAmount);
    }

    public boolean hasUserDonated(User user) {
        return donationRepository.existsByUser(user);
    }

    public double calculateTotalDonatedBy(User user) {
        Double total = transactionRepository.sumDonationsByUserId(user.getId());
        return total != null ? total : 0.0;
    }
}
