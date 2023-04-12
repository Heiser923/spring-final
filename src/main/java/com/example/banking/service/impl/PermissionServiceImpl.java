package com.example.banking.service.impl;

import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.repository.PermissionRepository;
import com.example.banking.service.PermissionService;
import com.example.banking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService{
    private PermissionRepository permissionRepository;
    private RoleService roleService;

    public PermissionServiceImpl(PermissionRepository permissionRepository, RoleService roleService) {
        this.permissionRepository = permissionRepository;
        this.roleService = roleService;
    }

    @Override
    public Permission addPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(permission)){
            permissionRepository.deleteById(id);
            return "Permission Has Been Deleted";
        }
        return "Permission " + id + " Doesn't Exist In The World";
    }

    @Override
    public String updateById(Long id, Permission permission) {
        Permission permissionToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(permissionToUpdate)){
            permissionToUpdate.setPermission_name(permission.getPermission_name());
            permissionRepository.save(permissionToUpdate);
            return "Update Successfully";
        }
        return "Update Not Successfully";
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> getByRoleId(Long roleId) {
        Role role = roleService.findById(roleId);
        return permissionRepository.findByRole(role);
    }
}
