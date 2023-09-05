package com.punit.failureTransactions.repository;

import com.punit.failureTransactions.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> getTransactionsByAccountNumber(long accountNumber);
}
