package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.CardStatus;
import com.example.banking.entities.Country;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.CountryRepository;
import com.example.banking.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return countryRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        countryRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Country country) {
        Country countryToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(countryToUpdate)){
            countryToUpdate.setCountry_name(country.getCountry_name());
            countryRepository.save(countryToUpdate);
        }
    }

    @Override
    public List<Country> getAll(Pagination pagination) {
        Page<Country> countries = countryRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(countries.getTotalElements());
        return countries.getContent();
    }
}
