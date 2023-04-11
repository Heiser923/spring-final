package com.example.banking.entities;
import com.example.banking.entities.bases.Base;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address extends Base {
    private String address_name;
    private String street_name;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country address_country;
}
