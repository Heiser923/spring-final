package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.CardProcessor;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.CardProcessorRequest;
import com.example.banking.service.CardProcessorService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/card-processor")
public class CardProcessorController {
    private CardProcessorService cardProcessorService;

    public CardProcessorController(CardProcessorService cardProcessorService) {
        this.cardProcessorService = cardProcessorService;
    }
    @PostMapping
    public ApiResponse addCardProcessor(@RequestBody CardProcessorRequest req){
        try {
            CardProcessor cardProcessor = new CardProcessor();
            BeanUtils.copyProperties(req, cardProcessor);
            cardProcessorService.addCardProcessor(cardProcessor);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<CardProcessor> getCardProcessorId(@PathVariable Long id){
        CardProcessor cardProcessor = cardProcessorService.findById(id);
        return new ApiResponse<CardProcessor>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cardProcessor);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCardProcessor(@PathVariable Long id){
        try {
            cardProcessorService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateCardProcessor(@PathVariable Long id, @RequestBody CardProcessorRequest req){
        try{
            CardProcessor cardProcessor = new CardProcessor();
            BeanUtils.copyProperties(req, cardProcessor);
            cardProcessorService.updateById(id, cardProcessor);
        }catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<CardProcessor>> getAllClient(Pagination pagination){
        List<CardProcessor> cardProcessors = cardProcessorService.getAll(pagination);
        return new ApiResponse<List<CardProcessor>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cardProcessors, pagination);
    }
}
