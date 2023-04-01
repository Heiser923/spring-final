package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.example.banking.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Client extends Base {

    private String firstName;
    private String lastName;
    private String ssn;
    private String displayName;
    private Gender gender;

    @OneToMany(mappedBy = "client")
    private Set<Account> accounts  = new HashSet<>();
}
