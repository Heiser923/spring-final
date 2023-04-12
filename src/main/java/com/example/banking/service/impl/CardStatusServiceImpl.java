package com.example.banking.service.impl;

import com.example.banking.entities.CardStatus;
import com.example.banking.entities.Country;
import com.example.banking.repository.CardStatusRepository;
import com.example.banking.service.CardStatusService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CardStatusServiceImpl implements CardStatusService {
    private CardStatusRepository cardStatusRepository;

    public CardStatusServiceImpl(CardStatusRepository cardStatusRepository) {
        this.cardStatusRepository = cardStatusRepository;
    }

    @Override
    public CardStatus addCardStatus(CardStatus cardStatus) {
        return cardStatusRepository.save(cardStatus);
    }

    @Override
    public CardStatus findById(Long id) {
        return cardStatusRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        CardStatus cardStatus = cardStatusRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(cardStatus)){
            cardStatusRepository.deleteById(id);
            return "Country Has Been Deleted";
        }
        return "Country " + id + " Doesn't Exist In The World";
    }

    @Override
    public String updateById(Long id, CardStatus cardStatus) {
        CardStatus cardStatusToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(cardStatusToUpdate)){
            cardStatusToUpdate.setStatus(cardStatus.getStatus());
            cardStatusRepository.save(cardStatusToUpdate);
            return "Update Successfully";
        }
        return "Update Not Successfully";
    }

    @Override
    public List<CardStatus> getAll() {
        return cardStatusRepository.findAll();
    }
}
