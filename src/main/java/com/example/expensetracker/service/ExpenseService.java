package com.example.expensetracker.service;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    // Constructor Injection (best practice)
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Add expense
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Delete expense by ID
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    // Get total expense for a specific month
    public double getMonthlyTotal(int year, int month) {
        return expenseRepository.findAll()
                .stream()
                .filter(expense ->
                        expense.getDate() != null &&
                        YearMonth.from(expense.getDate())
                                .equals(YearMonth.of(year, month)))
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
