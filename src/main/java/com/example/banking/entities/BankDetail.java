package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class BankDetail extends Base {
    private String bank_name;
    private String bank_description;
}
