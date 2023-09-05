package com.punit.transactions.factory;

import com.punit.transactions.config.SuccessTransactionConfig;
import com.punit.transactions.dto.ServerTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SuccessTransactionFactory implements TransactionFactory
{
    @Autowired
    private SuccessTransactionConfig successTransactionConfig;
    @Override
    public Map<String, Object> getTransaction(long accountNumber)
    {
        Map<String, Object> resp = new HashMap<>();
        try {
            List<ServerTransactionResponse> list = successTransactionConfig.getSuccessTransactions(accountNumber);
            resp.put("success", list);
            return resp;
        } catch (Exception e) {
            resp.put("success", "Server not responding");
            return resp;
        }
    }
}
