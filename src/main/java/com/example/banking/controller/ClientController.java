package com.example.banking.controller;

import com.example.banking.entities.Address;
import com.example.banking.entities.Client;
import com.example.banking.entities.enums.Gender;
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
        Client mockClient = new Client();
        BeanUtils.copyProperties(req, mockClient);
        mockClient.setGender(Gender.valueOf(req.getGender()));
        List<Address> addressesObjectList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(req.getAddresses())){
            System.out.println("Helloworld");
            for(Address addressAddReq : req.getAddresses()){
                Address addressObject = new Address();
                BeanUtils.copyProperties(addressAddReq, addressObject);
                addressObject.setClient(mockClient);
                addressesObjectList.add(addressObject);
            }
            mockClient.setAddresses(addressesObjectList);
        }
        clientService.addClient(mockClient);
        return req.toString();
    }

    @GetMapping("/{id}")
    public Client getClientId(@PathVariable Long id){
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id){
        return clientService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody ClientRequest req){
        Client mockClient = new Client();
        BeanUtils.copyProperties(req, mockClient);
        mockClient.setGender(Gender.valueOf(req.getGender()));
        return clientService.updateById(id, mockClient);
    }

    @GetMapping
    public List<Client> getAllClient(){
        return clientService.getAll();
    }
}
