package com.example.banking.repository;

import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
   List<Permission> findByRole(Role role);
}
