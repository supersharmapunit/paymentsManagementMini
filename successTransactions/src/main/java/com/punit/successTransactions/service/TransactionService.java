package com.punit.successTransactions.service;

import com.punit.successTransactions.dto.TransactionRequest;
import com.punit.successTransactions.models.Transaction;

import java.util.List;

public interface TransactionService {
    public void saveTransaction(TransactionRequest transactionRequest, long transactionId);
    public List<Transaction> getAllTransactions();
    public List<Transaction> getAllTransactionsByAccountNumber(long accountNumber);
    public Transaction fetchByAccountNumber(long accountNumber);
    public void deleteTransactionById(long transactionId);
}
