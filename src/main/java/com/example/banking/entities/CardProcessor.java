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
public class CardProcessor extends Base {
    private String processor_name;

    @OneToMany(mappedBy = "card_processor")
    private Set<Card> card_processor = new HashSet<>();

    @ManyToOne
    private Inspector processor_inspector;
}
