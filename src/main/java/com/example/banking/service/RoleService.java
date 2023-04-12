package com.example.banking.service;

import com.example.banking.entities.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    Role findById(Long id);
    String deleteById(Long id);
    String updateById(Long id, Role role);
    List<Role> getAll();
}
