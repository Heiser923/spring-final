package com.example.banking.service;

import com.example.banking.entities.CardType;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface CardTypeService {
    CardType addCardType(CardType cardtype);
    CardType findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, CardType cardtype);
    List<CardType> getAll(Pagination pagination);
}
