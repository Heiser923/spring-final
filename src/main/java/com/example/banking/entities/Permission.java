package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Permission extends Base {
    private String permission_name;

    @OneToMany(mappedBy = "cardPermission")
    private Set<Card> cards = new HashSet<>();

    @ManyToOne
    private Role Permission_role;
}
