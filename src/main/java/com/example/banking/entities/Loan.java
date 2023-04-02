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
public class Loan extends Base {
    private String loan_name;

    @OneToMany(mappedBy = "loan")
    private Set<Account> account = new HashSet<>();

    @ManyToOne
    private Amount loan_amount;
}
