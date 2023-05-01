package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.TransactionType;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.TransactionTypeRepository;
import com.example.banking.service.TransactionTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
    private TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }
    @Override
    public TransactionType addTransactionType(TransactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    @Override
    public TransactionType findById(Long id) {
        return transactionTypeRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        transactionTypeRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, TransactionType transactionType) {
        TransactionType transactionTypeToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(transactionTypeToUpdate)){
            transactionTypeToUpdate.setTransaction_type(transactionType.getTransaction_type());
            transactionTypeRepository.save(transactionTypeToUpdate);
        }
    }

    @Override
    public List<TransactionType> getAll(Pagination pagination) {
        Page<TransactionType> transactionTypes = transactionTypeRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(transactionTypes.getTotalElements());
        return transactionTypes.getContent();
    }
}
