package com.example.banking.entities;
import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address extends Base {
    private String address_name;
    private String street_name;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonIgnoreProperties("addresses")
    private Country address_country;
}
