package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction extends Base {
    private String transaction_name;
    @ManyToOne
    @JoinColumn(name = "cards")
    @JsonIgnoreProperties({"cards", "accounts", "transaction"})
    private Card cards;

    @ManyToOne
    @JoinColumn(name="amount")
    @JsonIgnoreProperties({"amount"})
    private Amount amount;

    @ManyToOne
    @JoinColumn(name = "transaction")
    @JsonIgnoreProperties({"transaction", "cards", "accounts"})
    private TransactionType transaction;

}
