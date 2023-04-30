package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permission extends Base {
    private String permission_name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("permissions")
    private Role role;
}
