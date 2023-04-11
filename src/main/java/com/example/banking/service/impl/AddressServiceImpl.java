package com.example.banking.service.impl;

import com.example.banking.entities.Address;
import com.example.banking.repository.AddressRepository;
import com.example.banking.service.AddressService;
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
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(address)){
            addressRepository.deleteById(id);
            return "Address Has Been Deleted";
        }
        return "Address " + id + " Doesn't Exist In The World";
    }

    @Override
    public String updateById(Long id, Address address) {
        Address addressToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(addressToUpdate)){
            addressToUpdate.setAddress_name(address.getAddress_name());
            addressRepository.save(addressToUpdate);
            return "Update Successfully";
        }
        return "Update Not Successfully";
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
