package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Country;
import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
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
    public ApiResponse addPermission(@RequestBody PermissionRequest req){
        Role role;
        try {
            role = roleService.findById(req.getRoleId());
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
        }
        Permission permission = new Permission();
        permission.setPermission_name(req.getPermission_name());
        permission.setRole(role);

        permissionService.addPermission(permission);
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Permission> getPermissionId(@PathVariable Long id){
        Permission permission = permissionService.findById(id);
        return new ApiResponse<Permission>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage());
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletePermission(@PathVariable Long id){
        try {
            permissionService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updatePermission(@PathVariable Long id, @RequestBody PermissionRequest req){
        try {
            Permission permission = new Permission();
            BeanUtils.copyProperties(req, permission);
            permissionService.updateById(id, permission);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Permission>> getAllPermission(Pagination pagination){
        List<Permission> permissions = permissionService.getAll(pagination);
        return new ApiResponse<List<Permission>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), permissions, pagination);
    }
}
