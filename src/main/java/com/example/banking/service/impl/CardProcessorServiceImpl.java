package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.CardProcessor;
import com.example.banking.entities.Card;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.CardProcessorRepository;
import com.example.banking.service.CardProcessorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CardProcessorServiceImpl implements CardProcessorService {
    private CardProcessorRepository cardProcessorRepository;

    public CardProcessorServiceImpl(CardProcessorRepository cardProcessorRepository) {
        this.cardProcessorRepository = cardProcessorRepository;
    }
    @Override
    public CardProcessor addCardProcessor(CardProcessor cardProcessor) {
        return cardProcessorRepository.save(cardProcessor);
    }

    @Override
    public CardProcessor findById(Long id) {
        return cardProcessorRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        cardProcessorRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, CardProcessor cardProcessor) {
        CardProcessor cardProcessorToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(cardProcessorToUpdate)){
            cardProcessorToUpdate.setCard_processor(cardProcessor.getCard_processor());
            cardProcessorRepository.save(cardProcessorToUpdate);
        }
    }

    @Override
    public List<CardProcessor> getAll(Pagination pagination) {
        Page<CardProcessor> cardProcessors = cardProcessorRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(cardProcessors.getTotalElements());
        return cardProcessors.getContent();
    }


}
