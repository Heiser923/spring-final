package com.example.banking.service;

import com.example.banking.entities.CardStatus;

import java.util.List;

public interface CardStatusService {

    CardStatus addCardStatus(CardStatus cardStatus);
    CardStatus findById(Long id);
    String deleteById(Long id);
    String updateById(Long id, CardStatus cardStatus);
    List<CardStatus> getAll();
}
