package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Amount;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.AmountRepository;
import com.example.banking.service.AmountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AmountServiceImpl implements AmountService {
    private AmountRepository amountRepoistory;

    public AmountServiceImpl(AmountRepository amountRepoistory) {
        this.amountRepoistory = amountRepoistory;
    }
    @Override
    public Amount addAmount(Amount amount) {
        return amountRepoistory.save(amount);
    }

    @Override
    public Amount findById(Long id) {
        return amountRepoistory.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        amountRepoistory.deleteById(id);
    }

    @Override
    public void updateById(Long id, Amount amount) {
        Amount amountToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(amountToUpdate)){
            amountToUpdate.setAmount(amount.getAmount());
            amountRepoistory.save(amountToUpdate);
        }
    }

    @Override
    public List<Amount> getAll(Pagination pagination) {
        Page<Amount> amounts = amountRepoistory.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(amounts.getTotalElements());
        return amounts.getContent();
    }
}
