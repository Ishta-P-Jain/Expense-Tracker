package com.example.expensetracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
public class ExpenseController {

    private final ExpenseService expenseService;

    // Constructor injection
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Add expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Delete expense
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    // Monthly summary
    @GetMapping("/monthly")
    public double getMonthlyTotal(
            @RequestParam int year,
            @RequestParam int month) {
        return expenseService.getMonthlyTotal(year, month);
    }
}
