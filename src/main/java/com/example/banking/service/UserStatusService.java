package com.example.banking.service;

import com.example.banking.entities.UserStatus;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface UserStatusService {
    UserStatus addUserStatus(UserStatus userStatus);
    UserStatus findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, UserStatus userStatus);
    List<UserStatus> getAll(Pagination pagination);
}
