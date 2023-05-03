package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Amount;
import com.example.banking.entities.Country;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.AmountRequest;
import com.example.banking.service.AmountService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/amount")
public class AmountController {
    AmountService amountService;

    public AmountController(AmountService amountService) {
        this.amountService = amountService;
    }

    @PostMapping
    public ApiResponse addAmount(@RequestBody AmountRequest req){
        try {
            Amount amount = new Amount();
            BeanUtils.copyProperties(req, amount);
            amountService.addAmount(amount);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Amount> getAmountId(@PathVariable Long id){
        Amount amount = amountService.findById(id);
        return new ApiResponse<Amount>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), amount);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteAmount(@PathVariable Long id){
        try {
            amountService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateAmount(@PathVariable Long id, @RequestBody AmountRequest req){
        try {
            Amount amount = new Amount();
            BeanUtils.copyProperties(req, amount);
            amountService.updateById(id, amount);
        } catch(NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Amount>> getAllAmount(Pagination pagination){
        List<Amount> amounts = amountService.getAll(pagination);
        return new ApiResponse<List<Amount>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), amounts, pagination);
    }
}
