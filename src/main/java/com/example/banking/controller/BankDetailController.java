package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.BankDetail;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
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
    private ApiResponse addBankDetail (@RequestBody BankDetailRequest req) {
        try {
            BankDetail bankDetail = new BankDetail();
            BeanUtils.copyProperties(req, bankDetail);
            bankDetailService.addBankDetail(bankDetail);
            return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
        } catch (NotFoundException e) {
            System.out.println(req.toString());
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
    }

    @PutMapping
    private ApiResponse updateBankDetail (@RequestBody BankDetailRequest req) {
        try{
            BankDetail bankDetail = new BankDetail();
            BeanUtils.copyProperties(req, bankDetail);
            bankDetailService.updateBankDetail(bankDetail);
            return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
    }

    @GetMapping
    private ApiResponse<BankDetail> getBankDetail () {
        BankDetail bankDetail = bankDetailService.getBankDetail();
        return new ApiResponse<BankDetail>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage() ,bankDetail);
    }
}
