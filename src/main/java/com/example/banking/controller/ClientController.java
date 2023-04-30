package com.example.banking.controller;

import com.example.banking.configuration.exceptions.NotFoundException;
import com.example.banking.configuration.exceptions.TransactionException;
import com.example.banking.entities.Address;
import com.example.banking.entities.Client;
import com.example.banking.entities.enums.Gender;
import com.example.banking.entities.response.ApiResponse;
import com.example.banking.entities.response.ApiStatus;
import com.example.banking.entities.response.Pagination;
import com.example.banking.request.ClientRequest;
import com.example.banking.service.AddressService;
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

    public ClientController(ClientService clientService, AddressService addressService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ApiResponse addClient(@RequestBody ClientRequest req){
        try {
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
        }catch (NotFoundException e) {
            throw new TransactionException(ApiStatus.FAI_CREATED.getCode(), ApiStatus.FAI_CREATED.getMessage());
        }
        return new ApiResponse(ApiStatus.SUC_CREATED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage());
    }

    @GetMapping("/{id}")
    public ApiResponse<Client> getClientId(@PathVariable Long id){
        Client client = clientService.findById(id);
        return new ApiResponse<Client>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), client);
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
    public ApiResponse updateClient(@PathVariable Long id, @RequestBody ClientRequest req){
        try {
            Client client = new Client();
            BeanUtils.copyProperties(req, client);
            client.setGender(Gender.valueOf(req.getGender()));
            clientService.updateById(id, client);
        } catch (NotFoundException e){
            throw new TransactionException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }

        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @GetMapping
    public ApiResponse<List<Client>> getAllClient(Pagination pagination){
        List<Client> clients = clientService.getAll(pagination);
        return new ApiResponse<List<Client>>(ApiStatus.SUC_RETRIEVED.getCode(), ApiStatus.SUC_RETRIEVED.getMessage(), clients, pagination);
    }
}
