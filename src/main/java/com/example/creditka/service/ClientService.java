package com.example.creditka.service;

import com.example.creditka.DTO.ClientDto;
import com.example.creditka.entity.Bank;
import com.example.creditka.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<Client> getAll();
    Client getOne(UUID uuid);
    void save(String name, String phone, String passportID, Bank bank);
    void update(Client clientEdit);
    void delete(Client client);
    Client getByPassportID(String passport);
    void deleteById(UUID uuid);
    Client getCreditOffers(UUID uuid);
}
