package com.punit.transactions.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryProvider{
    @Autowired
    private SuccessTransactionFactory successTransactionFactory;
    @Autowired
    private FailureTransactionFactory failureTransactionFactory;
    @Autowired
    private PendingTransactionFactory pendingTransactionFactory;
    @Autowired
    private InvalidTransactionFactory invalidTransactionFactory;

    public TransactionFactory getFactory(String status){
        switch(status.toLowerCase()){
            case "success":
                return successTransactionFactory;
            case "failure":
                return failureTransactionFactory;
            case "pending":
                return pendingTransactionFactory;
            default:
                return invalidTransactionFactory;
        }
    }
}