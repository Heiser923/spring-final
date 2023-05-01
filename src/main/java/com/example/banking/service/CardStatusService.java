package com.example.banking.service;

import com.example.banking.entities.CardStatus;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface CardStatusService {

    CardStatus addCardStatus(CardStatus cardStatus);
    CardStatus findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, CardStatus cardStatus);
    List<CardStatus> getAll(Pagination pagination);
}
