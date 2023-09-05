package com.punit.transactions.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Getter
@Setter
public class ServerTransactionResponse {
    private long transactionId;
    private long accountNumber;
    private int amount;
    private String status;
    private LocalDateTime createdAt;
}
