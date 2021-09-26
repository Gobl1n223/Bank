package com.example.creditka.service;


import com.example.creditka.entity.Bank;
import com.example.creditka.entity.Credit;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CreditService {
    List<Credit> getAll();
    Credit getOne(UUID uuid);
    List<Credit> getByBankID(UUID uuid);
    void update( BigDecimal creditLimit, BigDecimal percentRate, Bank bank);
    void delete(Credit credit);
    void save(Credit credit);
    void deleteById(UUID uuid);
}
