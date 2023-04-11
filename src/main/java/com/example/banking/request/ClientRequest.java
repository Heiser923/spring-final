package com.example.banking.request;

import com.example.banking.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String firstName;
    private String lastName;
    private String ssn;
    private String displayName;
    private String gender;
    private List<Address> addresses;
}
