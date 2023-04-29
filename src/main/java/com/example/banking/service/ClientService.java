package com.example.banking.service;

import com.example.banking.entities.Client;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    Client findById(Long id);
    boolean deleteById(Long id);
    String updateById(Long id, Client client);
    List<Client> getAll();
}
