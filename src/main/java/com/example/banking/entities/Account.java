package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Account extends Base {

    private String accountNumber;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "account")
    private Set<Card> cards = new HashSet<>();
}
