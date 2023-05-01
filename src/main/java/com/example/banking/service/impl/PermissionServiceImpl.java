package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Country;
import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.PermissionRepository;
import com.example.banking.service.PermissionService;
import com.example.banking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return permissionRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        permissionRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Permission permission) {
        Permission permissionToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(permissionToUpdate)){
            permissionToUpdate.setPermission_name(permission.getPermission_name());
            permissionRepository.save(permissionToUpdate);
        }
    }

    @Override
    public List<Permission> getAll(Pagination pagination) {
        Page<Permission> permissions = permissionRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(permissions.getTotalElements());
        return permissions.getContent();
    }

    @Override
    public List<Permission> getByRoleId(Long roleId) {
        Role role = roleService.findById(roleId);
        return permissionRepository.findByRole(role);
    }
}
