package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Permission;
import com.example.banking.entities.Role;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.RoleRepository;
import com.example.banking.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        roleRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Role role) {
        Role roleToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(roleToUpdate)){
            roleToUpdate.setRole_name(role.getRole_name());
            roleRepository.save(roleToUpdate);
        }
    }

    @Override
    public List<Role> getAll(Pagination pagination) {
        Page<Role> roles = roleRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(roles.getTotalElements());
        return roles.getContent();
    }
}
