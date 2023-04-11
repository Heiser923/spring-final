package com.example.banking.service;

import com.example.banking.entities.Address;
import java.util.List;

public interface AddressService {
    Address addAddress(Address address);
    Address findById(Long id);
    String deleteById(Long id);
    String updateById(Long id, Address address);
    List<Address> getAll();
}
