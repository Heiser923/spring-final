package com.example.banking.service;

import com.example.banking.entities.Account;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface AccountService {
    Account addAccount(Account account);
    Account findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Account account);
    List<Account> getAll(Pagination pagination);

    //List<Client> getAll(Pagination pagination);
}
