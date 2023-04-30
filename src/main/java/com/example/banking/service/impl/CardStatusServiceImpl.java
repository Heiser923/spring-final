package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Card;
import com.example.banking.entities.CardStatus;
import com.example.banking.entities.Country;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.CardStatusRepository;
import com.example.banking.service.CardStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return cardStatusRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        cardStatusRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, CardStatus cardStatus) {
        CardStatus cardStatusToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(cardStatusToUpdate)){
            cardStatusToUpdate.setStatus(cardStatus.getStatus());
            cardStatusRepository.save(cardStatusToUpdate);
        }
    }

    @Override
    public List<CardStatus> getAll(Pagination pagination) {
        Page<CardStatus> cardStatuses = cardStatusRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(cardStatuses.getTotalElements());
        return cardStatuses.getContent();
    }
}
