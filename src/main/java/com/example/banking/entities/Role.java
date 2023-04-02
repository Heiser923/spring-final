package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role extends Base {
    private String role_name;

    @OneToMany(mappedBy = "Permission_role")
    private Set<Permission> permissions = new HashSet<>();
}
