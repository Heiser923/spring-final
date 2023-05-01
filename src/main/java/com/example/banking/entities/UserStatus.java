package com.example.banking.entities;

import com.example.banking.entities.bases.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class UserStatus extends Base {
    private String user_status;

    @OneToMany(mappedBy = "user_status")
    @JsonIgnoreProperties({"status", "user_status"})
    private List<User> status;
}
