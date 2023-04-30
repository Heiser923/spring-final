package com.example.banking.service;

import com.example.banking.entities.Country;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);
    Country findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Country country);
    List<Country> getAll(Pagination pagination);
}
