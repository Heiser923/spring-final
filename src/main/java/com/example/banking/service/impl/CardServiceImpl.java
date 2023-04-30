package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Account;
import com.example.banking.entities.Card;
import com.example.banking.entities.CardStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.CardRepository;
import com.example.banking.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        cardRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Card card) {
        Card cardToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(cardToUpdate)){
            cardToUpdate.setCardNumber(card.getCardNumber());
//            cardToUpdate.setExpireDate(card.getExpireDate());
            cardRepository.save(cardToUpdate);
        }
    }

    @Override
    public List<Card> getAll(Pagination pagination) {
        Page<Card> cards = cardRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(cards.getTotalElements());
        return cards.getContent();
    }
}
