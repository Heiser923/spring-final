package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class User extends Base {
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("user")
    private Role user;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"status", "user_status"})
    private UserStatus user_status;
}
