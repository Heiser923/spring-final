package com.example.banking.service;

import com.example.banking.entities.Country;
import com.example.banking.entities.Permission;

import java.util.List;

public interface PermissionService {
    Permission addPermission(Permission permission);
    Permission findById(Long id);
    String deleteById(Long id);
    String updateById(Long id, Permission permission);
    List<Permission> getAll();
}
