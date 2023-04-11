package com.example.banking.controller;

import com.example.banking.entities.Client;
import com.example.banking.entities.Country;
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
    public String addAddress(@RequestBody CountryRequest req){
        Country country = new Country();
        BeanUtils.copyProperties(req, country);
        countryService.addCountry(country);
        return req.toString();
    }
    @GetMapping("/{id}")
    public Country getCountryId(@PathVariable Long id){
        return countryService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable Long id){
        return countryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public String updateCountry(@PathVariable Long id, @RequestBody CountryRequest req){
        Country country = new Country();
        BeanUtils.copyProperties(req, country);
        return countryService.updateById(id, country);
    }
    @GetMapping
    public List<Country> getAllClient(){
        return countryService.getAll();
    }
}
