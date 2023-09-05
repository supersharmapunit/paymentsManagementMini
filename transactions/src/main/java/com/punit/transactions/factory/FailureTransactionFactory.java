package com.punit.transactions.factory;

import com.punit.transactions.config.FailureTransactionConfig;
import com.punit.transactions.dto.ServerTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class FailureTransactionFactory implements TransactionFactory {
    @Autowired
    private FailureTransactionConfig failureTransactionConfig;

    @Override
    public Map<String, Object> getTransaction(long accountNumber) {
        Map<String, Object> resp = new HashMap<>();
        try {
            List<ServerTransactionResponse> list = failureTransactionConfig.getFailureTransactions(accountNumber);
            resp.put("failed", list);
            return resp;
        } catch (Exception e) {
            resp.put("failed", "Server not responding");
            return resp;
        }
    }
}
