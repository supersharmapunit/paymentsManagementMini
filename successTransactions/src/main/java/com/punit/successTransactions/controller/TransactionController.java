package com.punit.successTransactions.controller;

import com.punit.successTransactions.dto.TransactionRequest;
import com.punit.successTransactions.models.Transaction;
import com.punit.successTransactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction/success")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransactions() {
        return this.transactionService.getAllTransactions();
    }

    @GetMapping(path = "/a/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransactionsByAccountNumber(@PathVariable long accountNumber) {
        return this.transactionService.getAllTransactionsByAccountNumber((long)accountNumber);
    }

    @PostMapping(path = "/save/{accountNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable long accountNumber) {
        this.transactionService.saveTransaction(transactionRequest, accountNumber);
    }

    @DeleteMapping(path = "/delete/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransactionById(@PathVariable long transactionId) {
        this.transactionService.deleteTransactionById(transactionId);
    }
}