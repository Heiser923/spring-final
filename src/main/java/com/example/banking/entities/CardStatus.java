package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class CardStatus extends Base {

    private String status;

    @OneToMany (mappedBy = "card_status")
    private List<Card> cards;
}
