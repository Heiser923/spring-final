package com.example.banking.service;

import com.example.banking.entities.User;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, User user);
    List<User> getAll(Pagination pagination);
}
