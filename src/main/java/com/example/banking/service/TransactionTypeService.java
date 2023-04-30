package com.example.banking.service;

import com.example.banking.entities.TransactionType;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface TransactionTypeService {
    TransactionType addTransactionType(TransactionType transactionType);
    TransactionType findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, TransactionType transactionType);
    List<TransactionType> getAll(Pagination pagination);
}
