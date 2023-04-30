package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.TransactionType;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.TransactionTypeRequest;
import com.example.banking.service.TransactionTypeService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/transaction-type")
public class TransactionTypeController {
    private TransactionTypeService transactionTypeService;

    public TransactionTypeController(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    @PostMapping
    public ApiResponse addTransactionType(@RequestBody TransactionTypeRequest req){
        try {
            TransactionType transactionType = new TransactionType();
            BeanUtils.copyProperties(req, transactionType);
            transactionTypeService.addTransactionType(transactionType);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<TransactionType> getTransactionTypeId(@PathVariable Long id){
        TransactionType transactionType = transactionTypeService.findById(id);
        return new ApiResponse<TransactionType>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), transactionType);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteTransactionType(@PathVariable Long id){
        try {
            transactionTypeService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateTransactionType(@PathVariable Long id, @RequestBody TransactionTypeRequest req){
        try{
            TransactionType transactionType = new TransactionType();
            BeanUtils.copyProperties(req, transactionType);
            transactionTypeService.updateById(id, transactionType);
        }catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<TransactionType>> getAllClient(Pagination pagination){
        List<TransactionType> transactionTypes = transactionTypeService.getAll(pagination);
        return new ApiResponse<List<TransactionType>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), transactionTypes, pagination);
    }
}
