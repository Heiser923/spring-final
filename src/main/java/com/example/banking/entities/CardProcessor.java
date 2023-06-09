package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class CardProcessor extends Base {
    private String processor_name;

    @OneToMany(mappedBy = "card_processor")
    @JsonIgnoreProperties({"card_processor"})
    private List<Card> card_processor;
}
