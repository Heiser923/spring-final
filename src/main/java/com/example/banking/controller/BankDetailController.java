package com.example.banking.controller;

import com.example.banking.entities.BankDetail;
import com.example.banking.request.BankDetailRequest;
import com.example.banking.service.BankDetailService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/bank-detail")
public class BankDetailController {

    private BankDetailService bankDetailService;

    public BankDetailController(BankDetailService bankDetailService) {
        this.bankDetailService = bankDetailService;
    }

    @PostMapping
    private String addBankDetail (@RequestBody BankDetailRequest req) {
        BankDetail bankDetail = new BankDetail();
        BeanUtils.copyProperties(req, bankDetail);
        return bankDetailService.addBankDetail(bankDetail);
    }

    @PutMapping
    private String updateBankDetail (@RequestBody BankDetailRequest req) {
        BankDetail bankDetail = new BankDetail();
        BeanUtils.copyProperties(req, bankDetail);
        return bankDetailService.updateBankDetail(bankDetail);
    }

    @GetMapping
    private BankDetail getBankDetail () {
        return bankDetailService.getBankDetail();
    }
}
