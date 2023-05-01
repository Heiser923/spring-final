package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.User;
import com.example.banking.entities.UserStatus;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.UserRequest;
import com.example.banking.request.UserStatusRequest;
import com.example.banking.service.UserService;
import com.example.banking.service.UserStatusService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserStatusService userStatusService;

    public UserController(UserService userService, UserStatusService userStatusService) {
        this.userService = userService;
        this.userStatusService = userStatusService;
    }
    @PostMapping
    public ApiResponse addUser(@RequestBody UserRequest req){
        try {
            User user = new User();
            UserStatus userStatus = userStatusService.findById(req.getUser_status_id());
            BeanUtils.copyProperties(req, user);
            user.setUser_status(userStatus);
            userService.addUser(user);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<User> getUserId(@PathVariable Long id){
        User user = userService.findById(id);
        return new ApiResponse<User>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), user);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id){
        try {
            userService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id, @RequestBody UserStatusRequest req){
        try {
            User user = new User();
            BeanUtils.copyProperties(req, user);
            userService.updateById(id, user);
        } catch(NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<User>> getAllUser(Pagination pagination){
        List<User> users = userService.getAll(pagination);
        return new ApiResponse<List<User>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), users, pagination);
    }
}
