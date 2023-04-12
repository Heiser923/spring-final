package com.example.banking.controller;

import com.example.banking.entities.Address;
import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.request.RoleRequest;
import com.example.banking.service.RoleService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public String addRole(@RequestBody RoleRequest req){
        Role role = new Role();
        BeanUtils.copyProperties(req, role);
        List<Permission> permissionObjectSet = new ArrayList<>();
        if(!ObjectUtils.isEmpty(req.getPermissions())){
            for(Permission permissionReq : req.getPermissions()){
                Permission permissionObject = new Permission();
                BeanUtils.copyProperties(permissionReq, permissionObject);
                permissionObject.setRole(role);
                permissionObjectSet.add(permissionObject);
            }
            role.setPermissions(permissionObjectSet);
        }
        roleService.addRole(role);
        return req.toString();
    }
    @GetMapping("/{id}")
    public Role getRoleId(@PathVariable Long id){
        return roleService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id){
        return roleService.deleteById(id);
    }

    @PutMapping("/{id}")
    public String updateRole(@PathVariable Long id, @RequestBody RoleRequest req){
        Role role = new Role();
        BeanUtils.copyProperties(req, role);
        return roleService.updateById(id, role);
    }
    @GetMapping
    public List<Role> getAllRole(){
        return roleService.getAll();
    }
}
