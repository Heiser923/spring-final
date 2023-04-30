package com.example.banking.service;

import com.example.banking.entities.Role;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    Role findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Role role);
    List<Role> getAll(Pagination pagination);
}
