package com.example.creditka.service;

import com.example.creditka.entity.Bank;

import java.util.List;
import java.util.UUID;


public interface BankService {
    Bank getBankCredits(UUID uuid);
    Bank getById(UUID uuid);
    Bank getByName(String name);
    List<Bank> getAll();
    void deleteByID(UUID uuid);
    void save(Bank bank);
    void update(Bank bank);
    void delete(Bank bank);

}
