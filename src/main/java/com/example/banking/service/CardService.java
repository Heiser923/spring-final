package com.example.banking.service;

import com.example.banking.entities.Card;

import java.util.List;

public interface CardService {
    Card addCard(Card card);
    Card findById(Long id);
    String deleteById(Long id);
    String updateById(Long id, Card card);
    List<Card> getAll();
}
