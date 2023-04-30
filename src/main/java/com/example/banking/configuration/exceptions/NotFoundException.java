package com.example.banking.configuration.exceptions;

import com.example.banking.entities.response.ApiStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND,value = HttpStatus.NOT_FOUND,reason = "Hello World")
public class NotFoundException extends RuntimeException {

    private String code;
    private String message;


    public NotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NotFoundException () {
        this.code = ApiStatus.NOT_FOUND.getCode();
        this.message = ApiStatus.NOT_FOUND.getMessage();
    }
}
