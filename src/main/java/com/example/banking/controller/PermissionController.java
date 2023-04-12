package com.example.banking.controller;

import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.request.PermissionRequest;
import com.example.banking.service.PermissionService;
import com.example.banking.service.RoleService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private PermissionService permissionService;
    private RoleService roleService;

    public PermissionController(PermissionService permissionService, RoleService roleService) {
        this.permissionService = permissionService;
        this.roleService = roleService;
    }
    @PostMapping
    public String addPermission(@RequestBody PermissionRequest req){
        Role role = roleService.findById(req.getRoleId());

        if (role == null) {
            return "Role not found";
        }

        Permission permission = new Permission();
        permission.setPermission_name(req.getPermission_name());
        permission.setRole(role);

        permissionService.addPermission(permission);
        return req.toString();
    }
    @GetMapping("/{id}")
    public Permission getPermissionId(@PathVariable Long id){
        return permissionService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deletePermission(@PathVariable Long id){
        return permissionService.deleteById(id);
    }

    @PutMapping("/{id}")
    public String updatePermission(@PathVariable Long id, @RequestBody PermissionRequest req){
        Permission permission = new Permission();
        BeanUtils.copyProperties(req, permission);
        return permissionService.updateById(id, permission);
    }
    @GetMapping
    public List<Permission> getAllPermission(){
        return permissionService.getAll();
    }
}
