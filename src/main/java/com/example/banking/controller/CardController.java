package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Account;
import com.example.banking.entities.Card;
import com.example.banking.entities.CardType;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.CardRequest;
import com.example.banking.request.CardStatusRequest;
import com.example.banking.service.AccountService;
import com.example.banking.service.CardService;
import com.example.banking.service.CardTypeService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;
    private AccountService accountService;
    private CardTypeService cardTypeService;

    public CardController(CardService cardService, AccountService accountService, CardTypeService cardTypeService) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.cardTypeService = cardTypeService;
    }
    @PostMapping
    public ApiResponse addCard(@RequestBody CardRequest req){
        Account account;
        CardType cardType;
        try {
            account = accountService.findById(req.getAccountId());
            cardType = cardTypeService.findById(req.getCardTypeId());
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        Card card = new Card();
        BeanUtils.copyProperties(req, card);
        card.setAccounts(account);
        card.setCard_type(cardType);
        cardService.addCard(card);
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Card> getCardId(@PathVariable Long id){
        Card card = cardService.findById(id);
        return new ApiResponse<Card>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), card);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCard(@PathVariable Long id){
        try {
            cardService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateCard(@PathVariable Long id, @RequestBody CardStatusRequest req){
        try {
            Card card = new Card();
            BeanUtils.copyProperties(req, card);
            cardService.updateById(id, card);
        } catch(NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Card>> getAllCard(Pagination pagination){
        List<Card> cards = cardService.getAll(pagination);
        return new ApiResponse<List<Card>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), cards, pagination);
    }
}
