package com.example.banking.request;

import lombok.Data;

import java.sql.Time;

@Data
public class CardRequest {
    private String cardNumber;
    private Time expireDate;
}
