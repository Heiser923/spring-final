package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Address extends Base {
    private String address_name;
    private String street_name;

    @OneToMany(mappedBy = "client_address")
    private Set<Client> client_address = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Country country;
}
