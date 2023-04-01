package com.example.banking.entities.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");
    private final String code;
    Gender(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
