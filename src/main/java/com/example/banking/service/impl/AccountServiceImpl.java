package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Account;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        accountRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Account account) {
        Account accountToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(accountToUpdate)){
            accountToUpdate.setAccount_number(account.getAccount_number());
            accountRepository.save(accountToUpdate);
        }
    }

    @Override
    public List<Account> getAll(Pagination pagination) {
        Page<Account> accounts = accountRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(accounts.getTotalElements());
        return accounts.getContent();
    }
}
