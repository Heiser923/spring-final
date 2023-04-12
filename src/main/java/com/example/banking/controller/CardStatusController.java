package com.example.banking.controller;

import com.example.banking.entities.CardStatus;
import com.example.banking.request.CardRequest;
import com.example.banking.request.CardStatusRequest;
import com.example.banking.request.CountryRequest;
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
    public String addCard(@RequestBody CardStatusRequest req){
        CardStatus cardStatus = new CardStatus();
        BeanUtils.copyProperties(req, cardStatus);
        cardStatusService.addCardStatus(cardStatus);
        return req.toString();
    }
    @GetMapping("/{id}")
    public CardStatus getCountryId(@PathVariable Long id){
        return cardStatusService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable Long id){
        return cardStatusService.deleteById(id);
    }

    @PutMapping("/{id}")
    public String updateCountry(@PathVariable Long id, @RequestBody CardStatusRequest req){
        CardStatus cardStatus = new CardStatus();
        BeanUtils.copyProperties(req, cardStatus);
        return cardStatusService.updateById(id, cardStatus);
    }
    @GetMapping
    public List<CardStatus> getAllClient(){
        return cardStatusService.getAll();
    }
}
