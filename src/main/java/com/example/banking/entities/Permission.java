package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Permission extends Base {
    private String permission_name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne(mappedBy = "card_permission")
    private CardStatus card_status;
}
