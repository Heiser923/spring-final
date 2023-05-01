package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Address;
import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
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
    public ApiResponse addRole(@RequestBody RoleRequest req){
        try {
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
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Role> getRoleId(@PathVariable Long id){
        Role role = roleService.findById(id);
        return new ApiResponse<Role>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), role);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteRole(@PathVariable Long id){
        try {
            roleService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateRole(@PathVariable Long id, @RequestBody RoleRequest req){
        try {
            Role role = new Role();
            BeanUtils.copyProperties(req, role);
            roleService.updateById(id, role);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Role>> getAllRole(Pagination pagination){
        List<Role> roles = roleService.getAll(pagination);
        return new ApiResponse<List<Role>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), roles, pagination);
    }
}
