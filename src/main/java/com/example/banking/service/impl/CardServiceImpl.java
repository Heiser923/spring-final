package com.example.banking.service.impl;

import com.example.banking.entities.Card;
import com.example.banking.entities.CardStatus;
import com.example.banking.repository.CardRepository;
import com.example.banking.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(card)){
            cardRepository.deleteById(id);
            return "Country Has Been Deleted";
        }
        return "Country " + id + " Doesn't Exist In The World";
    }

    @Override
    public String updateById(Long id, Card card) {
        Card cardToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(cardToUpdate)){
            cardToUpdate.setCardNumber(card.getCardNumber());
            cardToUpdate.setExpireDate(card.getExpireDate());
            cardRepository.save(cardToUpdate);
            return "Update Successfully";
        }
        return "Update Not Successfully";
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }
}
