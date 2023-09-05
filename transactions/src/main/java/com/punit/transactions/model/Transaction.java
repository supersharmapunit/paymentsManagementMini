package com.punit.transactions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    long accountNumber;
    String status;
    int amount;
    LocalDateTime createdAt;
}
