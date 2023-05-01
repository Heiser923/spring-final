package com.example.banking.service;

import com.example.banking.entities.Client;
import com.example.banking.entities.response.Pagination;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    Client findById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Client client);
    List<Client> getAll(Pagination pagination);
}
