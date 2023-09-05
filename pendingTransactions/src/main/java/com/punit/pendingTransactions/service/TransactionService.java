package com.punit.pendingTransactions.service;

import com.punit.pendingTransactions.dto.TransactionRequest;
import com.punit.pendingTransactions.models.Transaction;

import java.util.List;

public interface TransactionService {
    public void saveTransaction(TransactionRequest transactionRequest, long transactionId);
    public List<Transaction> getAllTransactions();
    public List<Transaction> getAllTransactionsByAccountNumber(long accountNumber);
    public Transaction fetchByAccountNumber(long accountNumber);
    public void deleteTransactionById(long transactionId);
}