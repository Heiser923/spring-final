package com.example.banking.service;
import com.example.banking.entities.BankDetail;

public interface BankDetailService {
    void updateBankDetail (BankDetail bankDetail) ;

    BankDetail getBankDetail ();

    void addBankDetail(BankDetail bankDetail);
}
