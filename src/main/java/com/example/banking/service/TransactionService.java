package com.example.banking.service;

import com.example.banking.entities.Transaction;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    Transaction findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Transaction transaction);
    List<Transaction> getAll(Pagination pagination);
}
