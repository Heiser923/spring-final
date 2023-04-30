package com.example.banking.service;

import com.example.banking.entities.Address;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface AddressService {
    Address addAddress(Address address);
    Address findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Address address);
    List<Address> getAll(Pagination pagination);
}
