package com.example.banking.service.impl;

import com.example.banking.entities.Country;
import com.example.banking.repository.CountryRepository;
import com.example.banking.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Country country = countryRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(country)){
            countryRepository.deleteById(id);
            return "Country Has Been Deleted";
        }
        return "Country " + id + " Doesn't Exist In The World";
    }

    @Override
    public String updateById(Long id, Country country) {
        Country countryToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(countryToUpdate)){
            countryToUpdate.setCountry_name(country.getCountry_name());
            countryRepository.save(countryToUpdate);
            return "Update Successfully";
        }
        return "Update Not Successfully";
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}
