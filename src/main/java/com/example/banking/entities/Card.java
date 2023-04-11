package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Card extends Base {

    private String cardNumber;
    private Time expireDate;

    @ManyToOne
    private Account account_card;

    @ManyToOne
    private CardStatus card_status;
    @OneToMany (mappedBy = "card")
    private Set<Transaction> transaction = new HashSet<>();
    @ManyToOne
    private CardType card_type;
    @ManyToOne
    private CardProcessor card_processor;
}
