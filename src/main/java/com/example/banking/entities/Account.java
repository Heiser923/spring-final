package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Account extends Base {
    private String account_number;

    @ManyToOne
    @JsonIgnoreProperties("accounts")
    private Client client_account;

    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("accounts")
    private List<Card> cards;
}
