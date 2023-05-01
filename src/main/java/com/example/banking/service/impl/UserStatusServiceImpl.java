package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.UserStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.UserStatusRepository;
import com.example.banking.service.UserStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    private UserStatusRepository userStatusRepository;

    public UserStatusServiceImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public UserStatus addUserStatus(UserStatus userStatus) {
        return userStatusRepository.save(userStatus);
    }

    @Override
    public UserStatus findById(Long id) {
        return userStatusRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        userStatusRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserStatus userStatus) {
        UserStatus userStatusToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(userStatusToUpdate)){
            userStatusToUpdate.setUser_status(userStatus.getUser_status());
            userStatusRepository.save(userStatusToUpdate);
        }
    }

    @Override
    public List<UserStatus> getAll(Pagination pagination) {
        Page<UserStatus> userStatuses = userStatusRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(userStatuses.getTotalElements());
        return userStatuses.getContent();
    }
}
