package com.example.banking.controller;

import com.example.banking.entities.Address;
import com.example.banking.entities.Client;
import com.example.banking.request.AddressRequest;
import com.example.banking.service.AddressService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public String addAddress(@RequestBody AddressRequest req){
        Address address = new Address();
        BeanUtils.copyProperties(req, address);
        addressService.addAddress(address);
        return req.toString();
    }
    @GetMapping("/{id}")
    public Address getAddressId(@PathVariable Long id){
        return addressService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Long id){
        return addressService.deleteById(id);
    }

    @PutMapping("/{id}")
    public String updateAddress(@PathVariable Long id, @RequestBody AddressRequest req){
        Address address = new Address();
        BeanUtils.copyProperties(req, address);
        return addressService.updateById(id, address);
    }
    @GetMapping
    public List<Address> getAllClient(){
        return addressService.getAll();
    }
}
