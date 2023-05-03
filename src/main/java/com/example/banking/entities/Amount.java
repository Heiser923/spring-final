package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Amount extends Base {
    private Float amount;

    @OneToMany (mappedBy = "amount", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("amount")
    private List<Transaction> transaction;
}
