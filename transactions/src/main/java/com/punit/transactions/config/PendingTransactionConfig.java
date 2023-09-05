package com.punit.transactions.config;

import com.punit.transactions.dto.ServerTransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pending-transaction-service", url = "localhost:8083")
public interface PendingTransactionConfig {
    @GetMapping("/api/transaction/pending/a/{accountNumber}")
    public List<ServerTransactionResponse> getPendingTransactions(@PathVariable long accountNumber);
}
