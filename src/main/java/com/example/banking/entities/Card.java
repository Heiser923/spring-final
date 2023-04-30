package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
public class Card extends Base {

    private String cardNumber;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime expireDate;

    @ManyToOne
    @JoinColumn(name = "accounts")
    @JsonIgnoreProperties({"accounts", "cards"})
    private Account accounts;

    @ManyToOne
    private CardStatus card_status;
    @OneToMany (mappedBy = "cards", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("cards")
    private List<Transaction> transaction;
    @ManyToOne
    @JoinColumn(name="card_type")
    @JsonIgnoreProperties({"card_type","card"})
    private CardType card_type;
    @ManyToOne
    private CardProcessor card_processor;
}
