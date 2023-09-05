package com.punit.transactions.config;

import com.punit.transactions.dto.ServerTransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "failure-transaction-service", url = "localhost:8082")
public interface FailureTransactionConfig {
    @GetMapping("/api/transaction/failure/a/{accountNumber}")
    public List<ServerTransactionResponse> getFailureTransactions(@PathVariable long accountNumber);
}
