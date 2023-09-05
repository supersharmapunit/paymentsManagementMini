package com.punit.transactions.config;

import com.punit.transactions.dto.ServerTransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "success-transaction-service", url = "localhost:8081")
public interface SuccessTransactionConfig {
    @GetMapping("/api/transaction/success/a/{accountNumber}")
    public List<ServerTransactionResponse> getSuccessTransactions(@PathVariable long accountNumber);
}
