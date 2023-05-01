package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Transaction;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        transactionRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Transaction transaction) {
        Transaction transactionToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(transactionToUpdate)){
            transactionToUpdate.setTransaction_name(transaction.getTransaction_name());
            transactionRepository.save(transactionToUpdate);
        }
    }

    @Override
    public List<Transaction> getAll(Pagination pagination) {
        Page<Transaction> transactions = transactionRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(transactions.getTotalElements());
        return transactions.getContent();
    }
}
