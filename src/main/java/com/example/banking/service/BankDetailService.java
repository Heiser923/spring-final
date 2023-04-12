package com.example.banking.service;
import com.example.banking.entities.BankDetail;

public interface BankDetailService {
    String updateBankDetail (BankDetail bankDetail) ;

    BankDetail getBankDetail ();

    String addBankDetail(BankDetail bankDetail);
}
