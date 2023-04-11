package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.example.banking.entities.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Client extends Base {
    private String firstName;
    private String lastName;
    private String ssn;
    private String displayName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "client_account")
    private Set<Account> accounts  = new HashSet<>();
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("client")
    private List<Address> addresses;
}