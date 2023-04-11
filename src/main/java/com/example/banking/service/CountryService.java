package com.example.banking.service;

import com.example.banking.entities.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);
    Country findById(Long id);
    String deleteById(Long id);
    String updateById(Long id, Country country);
    List<Country> getAll();
}
