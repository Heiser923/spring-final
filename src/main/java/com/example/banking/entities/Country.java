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
public class Country extends Base {
    private String country_name;
    @OneToMany(mappedBy = "address_country", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("address_country")
    private List<Address> addresses;
}
