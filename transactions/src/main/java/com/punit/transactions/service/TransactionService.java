package com.punit.transactions.service;

import com.punit.transactions.factory.FactoryProvider;
import com.punit.transactions.factory.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {
    @Autowired
    private FactoryProvider factoryProvider;

    public Map<String, Object> getTransactions(long accountNumber, String status) {
        TransactionFactory fp =  factoryProvider.getFactory(status);
        CompletableFuture<Map<String, Object>> result = CompletableFuture.supplyAsync(() -> fp.getTransaction(accountNumber));
        return result.join();
    }

    public Map<String, Object> getAllTransactions(long accountNumber) {
        CompletableFuture<Map<String, Object>> successResult = CompletableFuture.supplyAsync(() -> factoryProvider.getFactory("success").getTransaction(accountNumber));
        CompletableFuture<Map<String, Object>> failedResult = CompletableFuture.supplyAsync(() -> factoryProvider.getFactory("failure").getTransaction(accountNumber));
        CompletableFuture<Map<String, Object>> pendingResult = CompletableFuture.supplyAsync(() -> factoryProvider.getFactory("pending").getTransaction(accountNumber));

        CompletableFuture<Void> allOf = CompletableFuture.allOf(successResult, failedResult, pendingResult);

        allOf.join(); // Wait for all API calls to complete

        // Retrieve the results
        Map<String, Object> success = successResult.join();
        Map<String, Object> failed = failedResult.join();
        Map<String, Object> pending = pendingResult.join();

        // Group the results into a Map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Success", success.get("success"));
        resultMap.put("Failed", failed.get("failed"));
        resultMap.put("Pending", pending.get("pending"));
        return resultMap;
    }
}