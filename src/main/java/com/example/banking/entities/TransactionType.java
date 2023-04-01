package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class TransactionType extends Base {

    private String name;

    @OneToMany (mappedBy = "transactionType")
    private Set<Transaction> transactions = new HashSet<>();

}
