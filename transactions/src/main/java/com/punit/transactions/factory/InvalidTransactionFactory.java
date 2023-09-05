package com.punit.transactions.factory;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
@Service
public class InvalidTransactionFactory implements TransactionFactory{

    @Override
    public Map<String, Object> getTransaction(long accountNumber) {
        return Collections.emptyMap();
    }
}
