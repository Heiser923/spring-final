package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Loan extends Base {
    private Float amount;

    @OneToMany(mappedBy = "loan")
    private Set<Account> account = new HashSet<>();
}
