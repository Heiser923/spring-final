package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.User;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.UserRepository;
import com.example.banking.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, User user) {
        User userToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(userToUpdate)){
            userToUpdate.setUsername(user.getUsername());
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public List<User> getAll(Pagination pagination) {
        Page<User> users = userRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(users.getTotalElements());
        return users.getContent();
    }
}
