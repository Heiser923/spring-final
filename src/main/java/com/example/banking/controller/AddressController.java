package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Address;
import com.example.banking.entities.Client;
import com.example.banking.entities.Country;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.AddressRequest;
import com.example.banking.service.AddressService;
import com.example.banking.service.CountryService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    private CountryService countryService;

    public AddressController(AddressService addressService, CountryService countryService) {
        this.addressService = addressService;
        this.countryService = countryService;
    }

    @PostMapping
    public ApiResponse addAddress(@RequestBody AddressRequest req){
        Country country;
        try {
            country = countryService.findById(req.getCountryId());
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        Address address = new Address();
        BeanUtils.copyProperties(req, address);
        address.setAddress_country(country);
        addressService.addAddress(address);
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Address> getAddressId(@PathVariable Long id){
        Address address = addressService.findById(id);
        return new ApiResponse<Address>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), address);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteAddress(@PathVariable Long id){
        try {
            addressService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateAddress(@PathVariable Long id, @RequestBody AddressRequest req){
        try {
            Address address = new Address();
            BeanUtils.copyProperties(req, address);
            addressService.updateById(id, address);
        } catch(NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Address>> getAllAddress(Pagination pagination){
        List<Address> addresses = addressService.getAll(pagination);
        return new ApiResponse<List<Address>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), addresses, pagination);
    }
}
