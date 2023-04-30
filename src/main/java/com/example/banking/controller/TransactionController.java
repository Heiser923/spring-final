package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Card;
import com.example.banking.entities.Transaction;
import com.example.banking.entities.TransactionType;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.TransactionRequest;
import com.example.banking.service.CardService;
import com.example.banking.service.TransactionService;
import com.example.banking.service.TransactionTypeService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Data
@RestController
    @RequestMapping("/transaction")
public class TransactionController {
    private TransactionTypeService transactionTypeService;
    private TransactionService transactionService;
    private CardService cardService;
    public TransactionController(TransactionService transactionService, CardService cardService,TransactionTypeService transactionTypeService){
        this.transactionService = transactionService;
        this.cardService = cardService;
        this.transactionTypeService = transactionTypeService;
    }

    @PostMapping
    public ApiResponse addTransaction(@RequestBody TransactionRequest req){
        Card card;
        TransactionType transactionType;
        try {
            card = cardService.findById(req.getCardId());
            transactionType = transactionTypeService.findById(req.getTransactionTypeId());
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(req, transaction);
        transaction.setCards(card);
        transaction.setTransaction(transactionType);
        transactionService.addTransaction(transaction);
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Transaction> getTransactionId(@PathVariable Long id){
        Transaction transaction = transactionService.findById(id);
        return new ApiResponse<Transaction>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), transaction);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteTransaction(@PathVariable Long id){
        try {
            transactionService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest req){
        try{
            Transaction transaction = new Transaction();
            BeanUtils.copyProperties(req, transaction);
            transactionService.updateById(id, transaction);
        }catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Transaction>> getAllTransaction(Pagination pagination){
        List<Transaction> transactions = transactionService.getAll(pagination);
        return new ApiResponse<List<Transaction>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), transactions, pagination);
    }
}
