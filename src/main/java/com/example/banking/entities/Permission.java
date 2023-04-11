package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Permission extends Base {
    private String permission_name;

    @ManyToOne
    private Role permission_role;
    @OneToOne(mappedBy = "card_permission")
    private CardStatus card_status;
}
