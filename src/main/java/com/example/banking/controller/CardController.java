package com.example.banking.controller;

import com.example.banking.entities.Card;
import com.example.banking.request.CardRequest;
import com.example.banking.request.CardStatusRequest;
import com.example.banking.service.CardService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
    @PostMapping
    public String addCard(@RequestBody CardRequest req){
        Card card = new Card();
        BeanUtils.copyProperties(req, card);
        cardService.addCard(card);
        return req.toString();
    }
    @GetMapping("/{id}")
    public Card getCardId(@PathVariable Long id){
        return cardService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteCard(@PathVariable Long id){
        return cardService.deleteById(id);
    }

    @PutMapping("/{id}")
    public String updateCard(@PathVariable Long id, @RequestBody CardStatusRequest req){
        Card card = new Card();
        BeanUtils.copyProperties(req, card);
        return cardService.updateById(id, card);
    }
    @GetMapping
    public List<Card> getAllCard(){
        return cardService.getAll();
    }
}
