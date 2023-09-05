package com.punit.pendingTransactions.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionRequest {
    private long transactionId;
    private int amount;
}
