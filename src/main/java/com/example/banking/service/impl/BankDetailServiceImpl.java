package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
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
    public void updateBankDetail(BankDetail bankDetail) {
        BankDetail bankDetail1 = bankDetailRepository.findById(1L).orElse(null);
        bankDetail1.setBankName(bankDetail.getBankName());
        bankDetail1.setBankDescription(bankDetail.getBankDescription());
        bankDetailRepository.save(bankDetail1);
    }

    @Override
    public BankDetail getBankDetail() {
        return bankDetailRepository.findById(1L).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void addBankDetail(BankDetail bankDetail) {
        BankDetail bankDetail1 = bankDetailRepository.findById(1L).orElse(null);
        if ( bankDetail1 == null) {
            bankDetailRepository.save(bankDetail);
        
        return bankDetailRepository.findById(1L).orElse(null);
    }
    }
}
