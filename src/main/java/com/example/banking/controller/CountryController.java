package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Client;
import com.example.banking.entities.Country;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.CountryRequest;
import com.example.banking.service.CountryService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping
    public ApiResponse addCountry(@RequestBody CountryRequest req){
        try {
            Country country = new Country();
            BeanUtils.copyProperties(req, country);
            countryService.addCountry(country);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_CREATED.getMessage());
    }
    @GetMapping("/{id}")
    public ApiResponse<Country> getCountryId(@PathVariable Long id){
        Country country = countryService.findById(id);
        return new ApiResponse<Country>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), country);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCountry(@PathVariable Long id){
        try {
            countryService.deleteById(id);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public ApiResponse updateCountry(@PathVariable Long id, @RequestBody CountryRequest req){
        try{
            Country country = new Country();
            BeanUtils.copyProperties(req, country);
            countryService.updateById(id, country);
        }catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_UPDATED.getCode(), ApiStatus.SUC_UPDATED.getMessage());
    }
    @GetMapping
    public ApiResponse<List<Country>> getAllClient(Pagination pagination){
        List<Country> countries = countryService.getAll(pagination);
        return new ApiResponse<List<Country>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), countries, pagination);
    }
}
