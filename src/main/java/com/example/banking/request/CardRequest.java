package com.example.banking.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    private String cardNumber;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime expireDate;
    private Long accountId;
    private Long cardTypeId;
}
