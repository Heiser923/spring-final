package com.example.configuration.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("field")
    private String field;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
}

