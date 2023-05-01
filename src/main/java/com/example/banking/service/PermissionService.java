package com.example.banking.service;

import com.example.banking.entities.Permission;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface PermissionService {
    Permission addPermission(Permission permission);
    Permission findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Permission permission);
    List<Permission> getAll(Pagination pagination);

    List<Permission> getByRoleId (Long roleId);
}
