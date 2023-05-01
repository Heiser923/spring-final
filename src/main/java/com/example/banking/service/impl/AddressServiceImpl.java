package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Account;
import com.example.banking.entities.Address;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.AddressRepository;
import com.example.banking.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        addressRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Address address) {
        Address addressToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(addressToUpdate)){
            addressToUpdate.setAddress_name(address.getAddress_name());
            addressRepository.save(addressToUpdate);
        }
    }

    @Override
    public List<Address> getAll(Pagination pagination) {
        Page<Address> addresses = addressRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(addresses.getTotalElements());
        return addresses.getContent();
    }
}
