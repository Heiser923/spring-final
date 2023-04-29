package com.example.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResource> genericException(NotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(ErrorResource.builder()
                .code(exception.getCode())
                .message(exception.getLocalizedMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TranscationException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResource> genericException(TranscationException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(ErrorResource.builder()
                .code(exception.getCode())
                .message(exception.getLocalizedMessage())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
