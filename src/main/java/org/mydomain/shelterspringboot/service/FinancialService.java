package org.mydomain.shelterspringboot.service;

import jakarta.transaction.Transactional;
import org.mydomain.shelterspringboot.model.FinancialTransaction;
import org.mydomain.shelterspringboot.model.Staff;
import org.mydomain.shelterspringboot.model.User;
import org.mydomain.shelterspringboot.repository.FinancialTransactionRepository;
import org.mydomain.shelterspringboot.service.DonationService;
import org.mydomain.shelterspringboot.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialService {
    private final FinancialTransactionRepository transactionRepository;
    private final DonationService donationService;
    private final ExpenseService expenseService;

    public FinancialService(FinancialTransactionRepository transactionRepository,
                            DonationService donationService,
                            ExpenseService expenseService) {
        this.transactionRepository = transactionRepository;
        this.donationService = donationService;
        this.expenseService = expenseService;
    }

    public double getBalance() {
        Double donations = transactionRepository.sumAllDonations();
        Double expenses = transactionRepository.sumAllExpenses();
        return (donations != null ? donations : 0.0) - (expenses != null ? expenses : 0.0);
    }

    @Transactional
    public void registerDonation(User user, double amount) {
        donationService.registerDonation(user, amount);
    }

    @Transactional
    public void registerExpense(Staff staff, Long dogId, double amount, String description) {
        expenseService.registerExpense(staff, dogId, amount, description, getBalance());
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
}
