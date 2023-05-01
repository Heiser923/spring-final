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

<<<<<<< Updated upstream
    @OneToMany(mappedBy = "account")
    private Set<Card> cards = new HashSet<>();

    @ManyToOne
    private Loan loan;
=======
    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("accounts")
    private List<Card> cards;
>>>>>>> Stashed changes
}
