package com.punit.successTransactions.dto;

import jakarta.persistence.Id;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionRequest {
    private long transactionId;
    private int amount;
}
