package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Country extends Base {
    private String country_name;
    @OneToOne(mappedBy = "address_country")
    private Address address_country;
}
