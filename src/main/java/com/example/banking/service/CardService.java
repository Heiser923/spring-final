package com.example.banking.service;

import com.example.banking.entities.Card;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface CardService {
    Card addCard(Card card);
    Card findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Card card);
    List<Card> getAll(Pagination pagination);
}
