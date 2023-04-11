package com.example.banking.service.impl;

import com.example.banking.entities.Address;
import com.example.banking.entities.Client;
import com.example.banking.entities.enums.Gender;
import com.example.banking.repository.ClientRepository;
import com.example.banking.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(client)){
            clientRepository.deleteById(id);
            return "Client Has Been Deleted";
        }
        return "Client " + id + " Doesn't Exist In The World";
    }

    @Override
    public Client updateById(Long id, Client client) {
        Client clientToUpdate = this.findById(id);
        if(!ObjectUtils.isEmpty(clientToUpdate)){
            clientToUpdate.setFirstName(client.getFirstName());
            clientToUpdate.setLastName(client.getLastName());
            clientToUpdate.setGender(client.getGender());
            clientToUpdate.setSsn(client.getSsn());
            clientToUpdate.setDisplayName(client.getDisplayName());
//            List<Address> addressList = new ArrayList<>();
//            if(!ObjectUtils.isEmpty(client.getAddresses())){
//                System.out.println(client.getAddresses().toString());
//                for(Address adr : client.getAddresses()){
//                    addressList.add(adr);
//                    adr.setClient_address(clientToUpdate);
//                }
//                clientToUpdate.getAddresses().clear();
//                clientToUpdate.getAddresses().addAll(addressList);
//            }
            return clientRepository.save(clientToUpdate);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

}
