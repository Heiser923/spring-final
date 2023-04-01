package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Inspector extends Base {
    private String inspector_name;

    @OneToMany(mappedBy = "processor_inspector")
    private Set<CardProcessor> processor_inspector = new HashSet<>();
}
