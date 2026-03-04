package org.mydomain.shelterspringboot.service;

import jakarta.transaction.Transactional;
import org.mydomain.shelterspringboot.model.Dog;
import org.mydomain.shelterspringboot.model.Expense;
import org.mydomain.shelterspringboot.model.Staff;
import org.mydomain.shelterspringboot.repository.DogRepository;
import org.mydomain.shelterspringboot.repository.ExpenseRepository;
import org.mydomain.shelterspringboot.repository.FinancialTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final DogRepository dogRepository;

    public ExpenseService(ExpenseRepository expenseRepository, DogRepository dogRepository, FinancialTransactionRepository transactionRepository) {
        this.expenseRepository = expenseRepository;
        this.dogRepository = dogRepository;
    }

    @Transactional
    public void registerExpense(Staff staff, Long dogId, double amount, String description, double currentBalance) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new IllegalArgumentException("Dog not found."));
        if (amount > currentBalance) throw new IllegalArgumentException("Insufficient funds.");
        expenseRepository.save(new Expense(amount, LocalDate.now(), description, dog, staff));
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
}