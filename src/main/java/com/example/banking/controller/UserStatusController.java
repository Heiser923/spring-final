package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.UserStatus;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.UserStatusRequest;
import com.example.banking.service.UserStatusService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/user-status")
public class UserStatusController {
    private UserStatusService userStatusService;

    public UserStatusController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }
    @PostMapping
    public ApiResponse addUserStatus(@RequestBody UserStatusRequest req){
        try {
            UserStatus userStatus = new UserStatus();
            BeanUtils.copyProperties(req, userStatus);
            userStatusService.addUserStatus(userStatus);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<UserStatus> getUserStatusId(@PathVariable Long id){
        UserStatus userStatus = userStatusService.findById(id);
        return new ApiResponse<UserStatus>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), userStatus);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteUserStatus(@PathVariable Long id){
        try {
            userStatusService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateUserStatus(@PathVariable Long id, @RequestBody UserStatusRequest req){
        try {
            UserStatus userStatus = new UserStatus();
            BeanUtils.copyProperties(req, userStatus);
            userStatusService.updateById(id, userStatus);
        } catch(NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<UserStatus>> getAllUserStatus(Pagination pagination){
        List<UserStatus> userStatuses = userStatusService.getAll(pagination);
        return new ApiResponse<List<UserStatus>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), userStatuses, pagination);
    }
}
