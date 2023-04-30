package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.CardType;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.CardTypeRepository;
import com.example.banking.service.CardTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CardTypeServiceImpl implements CardTypeService {
    private CardTypeRepository cardTypeRepository;

    public CardTypeServiceImpl(CardTypeRepository cardTypeRepository) {
        this.cardTypeRepository = cardTypeRepository;
    }

    @Override
    public CardType addCardType(CardType cardType) {
        return cardTypeRepository.save(cardType);
    }

    @Override
    public CardType findById(Long id) {
        return cardTypeRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        cardTypeRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, CardType cardType) {
        CardType cardTypeToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(cardTypeToUpdate)){
            cardTypeToUpdate.setCard_type_name(cardType.getCard_type_name());
            cardTypeRepository.save(cardTypeToUpdate);
        }
    }

    @Override
    public List<CardType> getAll(Pagination pagination) {
        Page<CardType> countries = cardTypeRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(countries.getTotalElements());
        return countries.getContent();
    }
}
