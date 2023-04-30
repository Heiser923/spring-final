package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Address;
import com.example.banking.entities.Client;
import com.example.banking.entities.enums.Gender;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.request.ClientRequest;
import com.example.banking.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public String addClient(@RequestBody ClientRequest req){
        Client client = new Client();
        BeanUtils.copyProperties(req, client);
        client.setGender(Gender.valueOf(req.getGender()));
        List<Address> addressesObjectList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(req.getAddresses())){
            for(Address addressAddReq : req.getAddresses()){
                Address addressObject = new Address();
                BeanUtils.copyProperties(addressAddReq, addressObject);
                addressObject.setClient(client);
                addressesObjectList.add(addressObject);
            }
            client.setAddresses(addressesObjectList);
        }
        clientService.addClient(client);
        return req.toString();
    }

    @GetMapping("/{id}")
    public Client getClientId(@PathVariable Long id){
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteClient(@PathVariable Long id){
        try {
            clientService.deleteById(id);
        } catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_DELETED.getCode(), ApiStatus.FAI_DELETED.getMessage());
        }

        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @PutMapping("/{id}")
    public String updateClient(@PathVariable Long id, @RequestBody ClientRequest req){
        Client client = new Client();
        BeanUtils.copyProperties(req, client);
        client.setGender(Gender.valueOf(req.getGender()));
        return clientService.updateById(id, client);
    }

    @GetMapping
    public List<Client> getAllClient(){
        return clientService.getAll();
    }
}
