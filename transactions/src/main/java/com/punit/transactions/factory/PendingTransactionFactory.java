package com.punit.transactions.factory;

import com.punit.transactions.config.PendingTransactionConfig;
import com.punit.transactions.dto.ServerTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PendingTransactionFactory implements TransactionFactory{
    @Autowired
    private PendingTransactionConfig pendingTransactionConfig;

    @Override
    public Map<String, Object> getTransaction(long accountNumber) {
        Map<String, Object> resp = new HashMap<>();
        try {
            List<ServerTransactionResponse> list = pendingTransactionConfig.getPendingTransactions(accountNumber);
            resp.put("pending", list);
            return resp;
        } catch (Exception e) {
            resp.put("pending", "Server not responding");
            return resp;
        }
    }
}
