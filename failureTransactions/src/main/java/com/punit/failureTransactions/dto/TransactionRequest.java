package com.punit.failureTransactions.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionRequest {
    private int amount;
    private long transactionId;
}
