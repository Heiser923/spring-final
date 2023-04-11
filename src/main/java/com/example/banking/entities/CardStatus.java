package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class CardStatus extends Base {

    private String status;

    @OneToMany (mappedBy = "card_status")
    private Set<Card> cards = new HashSet<>();

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "card_permission_id", referencedColumnName = "id")
    private Permission card_permission;
}
