package com.punit.transactions.controller;

import com.punit.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsByStatus(@PathVariable long accountNumber, @RequestParam String status) {

        if(status.equalsIgnoreCase("all")) {
            return transactionService.getAllTransactions(accountNumber);
        } else {
            return transactionService.getTransactions(accountNumber, status);
        }
    }
}
