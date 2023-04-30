package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Account;
import com.example.banking.entities.Client;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.request.AccountRequest;
import com.example.banking.service.AccountService;
import com.example.banking.service.ClientService;
import com.example.banking.entities.response.Pagination;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private ClientService clientService;

    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @PostMapping
    public ApiResponse addAccount(@RequestBody AccountRequest req){
        Client client;
        try {
            client = clientService.findById(req.getClientId());
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        Account account = new Account();
        account.setAccount_number(req.getAccount_number());
        account.setClient_account(client);
        accountService.addAccount(account);

        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Account> getAccountId(@PathVariable Long id){
        Account account = accountService.findById(id);
        return new ApiResponse<Account>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), account);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteAccount(@PathVariable Long id){
        try {
            accountService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateAccount(@PathVariable Long id, @RequestBody AccountRequest req){
        try {
            Account account = new Account();
            BeanUtils.copyProperties(req, account);
            accountService.updateById(id, account);
        } catch(NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Account>> getAllAccount(Pagination pagination){
        List<Account> accounts = accountService.getAll(pagination);
        return new ApiResponse<List<Account>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), accounts, pagination);
    }
}
