package org.mydomain.shelterspringboot.controller;

import org.mydomain.shelterspringboot.model.Dog;
import org.mydomain.shelterspringboot.model.Expense;
import org.mydomain.shelterspringboot.model.Staff;
import org.mydomain.shelterspringboot.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerExpense(
            @RequestBody Staff staff,
            @RequestParam Long dogId,
            @RequestParam double amount,
            @RequestParam String description,
            @RequestParam double currentBalance) {
        expenseService.registerExpense(staff, dogId, amount, description, currentBalance);
    }

    @GetMapping("/dog")
    public List<Expense> getExpensesByDog(@RequestBody Dog dog) {
        return expenseService.getExpensesByDog(dog);
    }

    @GetMapping("/staff")
    public List<Expense> getExpensesByStaff(@RequestBody Staff staff) {
        return expenseService.getExpensesByStaff(staff);
    }

    @GetMapping("/search")
    public List<Expense> searchExpenses(@RequestParam String keyword) {
        return expenseService.searchExpensesByDescription(keyword);
    }
}