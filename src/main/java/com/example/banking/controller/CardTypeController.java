package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.CardType;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.CardTypeRequest;
import com.example.banking.service.CardTypeService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/card-type")
public class CardTypeController {
    private CardTypeService cardTypeService;

    public CardTypeController(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }
    @PostMapping
    public ApiResponse addCardType(@RequestBody CardTypeRequest req){
        try {
            CardType cardType = new CardType();
            BeanUtils.copyProperties(req, cardType);
            cardTypeService.addCardType(cardType);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<CardType> getCardTypeId(@PathVariable Long id){
        CardType cardType = cardTypeService.findById(id);
        return new ApiResponse<CardType>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cardType);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCardType(@PathVariable Long id){
        try {
            cardTypeService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateCardType(@PathVariable Long id, @RequestBody CardTypeRequest req){
        try{
            CardType cardType = new CardType();
            BeanUtils.copyProperties(req, cardType);
            cardTypeService.updateById(id, cardType);
        }catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<CardType>> getAllClient(Pagination pagination){
        List<CardType> cardTypes = cardTypeService.getAll(pagination);
        return new ApiResponse<List<CardType>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cardTypes, pagination);
    }
}
