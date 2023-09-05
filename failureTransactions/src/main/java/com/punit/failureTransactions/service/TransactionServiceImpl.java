package com.punit.failureTransactions.service;

import com.punit.failureTransactions.dto.TransactionRequest;
import com.punit.failureTransactions.models.Transaction;
import com.punit.failureTransactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(TransactionRequest transactionRequest, long accountNumber) {
        try {
            Transaction transaction = mapToTransaction(transactionRequest, accountNumber, LocalDateTime.now());
            this.transactionRepository.save(transaction);
            System.out.println("Transaction saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransactionById(long transactionId) {
        try {
            this.transactionRepository.deleteById(transactionId);
            System.out.println("Transaction deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountNumber(long accountNumber) {
        return this.transactionRepository.getTransactionsByAccountNumber(accountNumber);
    }

    @Override
    public Transaction fetchByAccountNumber(long accountNumber) {
        return null;
    }

    public Transaction mapToTransaction(TransactionRequest transactionRequest, long accountNumber, LocalDateTime createdAt) {

        return Transaction.builder()
                .transactionId(transactionRequest.getTransactionId())
                .amount(transactionRequest.getAmount())
                .accountNumber(accountNumber)
                .status("failed")
                .createdAt(createdAt)
                .build();
    }
}