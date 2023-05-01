package com.example.banking.service;
import com.example.banking.entities.CardProcessor;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface CardProcessorService {
    CardProcessor addCardProcessor(CardProcessor cardProcessor);
    CardProcessor findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, CardProcessor cardProcessor);
    List<CardProcessor> getAll(Pagination pagination);
}
