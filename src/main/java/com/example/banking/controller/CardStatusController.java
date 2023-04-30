package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.CardStatus;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.CardStatusRequest;
import com.example.banking.service.CardStatusService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/card-status")
public class CardStatusController {
    private CardStatusService cardStatusService;

    public CardStatusController(CardStatusService cardStatusService) {
        this.cardStatusService = cardStatusService;
    }

    @PostMapping
    public ApiResponse addCardStatus(@RequestBody CardStatusRequest req){
        try {
            CardStatus cardStatus = new CardStatus();
            BeanUtils.copyProperties(req, cardStatus);
            cardStatusService.addCardStatus(cardStatus);
            return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse<CardStatus> getCardStatusId(@PathVariable Long id){
        CardStatus cardStatus = cardStatusService.findById(id);
        return new ApiResponse<CardStatus>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cardStatus);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCardStatus(@PathVariable Long id){
        try {
            cardStatusService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateCardStatus(@PathVariable Long id, @RequestBody CardStatusRequest req){
        try {
            CardStatus cardStatus = new CardStatus();
            BeanUtils.copyProperties(req, cardStatus);
            cardStatusService.updateById(id, cardStatus);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<CardStatus>> getAllCardStatus(Pagination pagination){
        List<CardStatus> cardStatuses = cardStatusService.getAll(pagination);
        return new ApiResponse<List<CardStatus>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cardStatuses, pagination);
    }
}
