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
public class Role extends Base {
    private String role_name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("role")
    private List<Permission> permissions;
}
