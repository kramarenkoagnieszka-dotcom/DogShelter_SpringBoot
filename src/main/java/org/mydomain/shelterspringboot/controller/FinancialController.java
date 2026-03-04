package org.mydomain.shelterspringboot.controller;

import org.mydomain.shelterspringboot.model.FinancialTransaction;
import org.mydomain.shelterspringboot.model.Staff;
import org.mydomain.shelterspringboot.model.User;
import org.mydomain.shelterspringboot.service.FinancialService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/financial")
public class FinancialController {

    private final FinancialService financialService;

    public FinancialController(FinancialService financialService) {
        this.financialService = financialService;
    }

    @GetMapping("/balance")
    public double getBalance() {
        return financialService.getBalance();
    }

    @PostMapping("/donation")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerDonation(@RequestBody User user, @RequestParam double amount) {
        financialService.registerDonation(user, amount);
    }

    @PostMapping("/expense")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerExpense(
            @RequestBody Staff staff,
            @RequestParam Long dogId,
            @RequestParam double amount,
            @RequestParam String description) {
        financialService.registerExpense(staff, dogId, amount, description);
    }

    @GetMapping("/transactions/range")
    public List<FinancialTransaction> getTransactionsInRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return financialService.getTransactionsInRange(start, end);
    }

    @GetMapping("/transactions/recent")
    public List<FinancialTransaction> getRecentTransactions() {
        return financialService.getRecentTransactions();
    }
}
