package com.punit.transactions.factory;

import java.util.Map;

public interface TransactionFactory {
    public Map<String, Object> getTransaction(long accountNumber);
}
