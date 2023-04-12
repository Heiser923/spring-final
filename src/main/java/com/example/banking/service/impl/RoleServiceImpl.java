package com.example.banking.service.impl;

import com.example.banking.entities.Role;
import com.example.banking.repository.RoleRepository;
import com.example.banking.service.RoleService;
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
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(role)){
            roleRepository.deleteById(id);
            return "Role Has Been Deleted";
        }
        return "Role " + id + " Doesn't Exist In The World";
    }

    @Override
    public String updateById(Long id, Role role) {
        Role roleToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(roleToUpdate)){
            roleToUpdate.setRole_name(role.getRole_name());
            roleRepository.save(roleToUpdate);
            return "Update Successfully";
        }
        return "Update Not Successfully";
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
