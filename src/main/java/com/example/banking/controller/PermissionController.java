package com.example.banking.controller;

import com.example.banking.entities.Permission;
import com.example.banking.request.PermissionRequest;
import com.example.banking.service.PermissionService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    @PostMapping
    public String addPermission(@RequestBody PermissionRequest req){
        Permission permission = new Permission();
        BeanUtils.copyProperties(req, permission);
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
