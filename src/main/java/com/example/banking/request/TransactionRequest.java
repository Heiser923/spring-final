package com.example.banking.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String transaction_name;
    private Long cardId;
    private Long transactionTypeId;
}
