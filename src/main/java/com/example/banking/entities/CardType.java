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
public class CardType extends Base {
    private String card_type_name;

    @OneToMany (mappedBy = "card_type", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("card_type")
    private List<Card> card;
}
