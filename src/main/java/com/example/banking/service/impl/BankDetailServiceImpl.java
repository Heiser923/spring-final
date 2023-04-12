package com.example.banking.service.impl;

import com.example.banking.entities.BankDetail;
import com.example.banking.repository.BankDetailRepository;
import com.example.banking.service.BankDetailService;
import org.springframework.stereotype.Service;

@Service
public class BankDetailServiceImpl implements BankDetailService {
    private BankDetailRepository bankDetailRepository;

    public BankDetailServiceImpl(BankDetailRepository bankDetailRepository) {
        this.bankDetailRepository = bankDetailRepository;
    }

    @Override
    public String updateBankDetail(BankDetail bankDetail) {
        BankDetail bankDetail1 = bankDetailRepository.findById(1L).orElse(null);

        if ( bankDetail1 == null) {
            return "Bank Detail Does Not Exist.";
        }

        bankDetail1.setBankName(bankDetail.getBankName());
        bankDetail1.setBankDescription(bankDetail.getBankDescription());
        bankDetailRepository.save(bankDetail1);
        return "Updated Bank Detail";
    }

    @Override
    public BankDetail getBankDetail() {
        return bankDetailRepository.findById(1L).orElse(null);
    }

    @Override
    public String addBankDetail(BankDetail bankDetail) {
        BankDetail bankDetail1 = bankDetailRepository.findById(1L).orElse(null);
        if ( bankDetail1 == null) {
            bankDetailRepository.save(bankDetail);
            return "Saved Bank Detail";
        }

        return "Bank Detail Already Exists Update it instead";
    }
}
