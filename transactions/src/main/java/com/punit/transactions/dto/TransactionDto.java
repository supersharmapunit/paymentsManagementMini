package com.punit.transactions.dto;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Getter
@Setter
public class TransactionDto {
    private Map<String, Object> transactionDetails;
}
