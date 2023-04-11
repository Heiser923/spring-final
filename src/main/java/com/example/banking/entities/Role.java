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

    @OneToMany(mappedBy = "permission_role")
    private Set<Permission> permission_role = new HashSet<>();
}
