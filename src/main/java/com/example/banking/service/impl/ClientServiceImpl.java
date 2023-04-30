package com.example.banking.service.impl;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.entities.Client;
import com.example.banking.entities.response.Pagination;
import com.example.banking.repository.ClientRepository;
import com.example.banking.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        return clientRepository.findById(id).orElseThrow( () -> new NotFoundException());
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        clientRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Client client) {
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
            clientRepository.save(clientToUpdate);
        }
    }

    @Override
    public List<Client> getAll(Pagination pagination) {
        Page<Client> clients = clientRepository.findAll(PageRequest.of(pagination.getIndexPageable(), pagination.getSize()));
        pagination.setTotalCounts(clients.getTotalElements());
        return clients.getContent();
    }

}
