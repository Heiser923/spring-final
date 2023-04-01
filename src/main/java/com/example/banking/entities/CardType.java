package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class CardType extends Base {
    private String card_name;

    @OneToMany (mappedBy = "cardType")
    private Set<Card> card = new HashSet<>();
}
