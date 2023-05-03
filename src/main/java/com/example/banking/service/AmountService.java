package com.example.banking.service;

import com.example.banking.entities.Amount;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface AmountService {
    Amount addAmount(Amount amount);
    Amount findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Amount amount);
    List<Amount> getAll(Pagination pagination);
}
