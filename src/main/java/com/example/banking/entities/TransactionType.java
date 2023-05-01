package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class TransactionType extends Base {

    private String transaction_type;

    @OneToMany (mappedBy = "transaction", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("transaction")
    private List<Transaction> transaction;

}
