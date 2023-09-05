package com.punit.successTransactions.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "success_Transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Getter
@Setter
public class Transaction {
    @Id
    private long transactionId;
    private long accountNumber;
    private int amount;
    private String status;
    private LocalDateTime createdAt;
}
