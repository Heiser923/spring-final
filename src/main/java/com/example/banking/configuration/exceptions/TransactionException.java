package com.example.banking.configuration.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Internal Server Error!")
public class TransactionException extends RuntimeException {

    private String code;
    private String message;


    public TransactionException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
