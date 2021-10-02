package com.example.creditka.service.impl;


import com.example.creditka.entity.Bank;
import com.example.creditka.DAO.BankDAO;
import com.example.creditka.service.IService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class BankServiceImpl implements IService<Bank> {

    private final BankDAO bankDAO;

    public BankServiceImpl(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Override
    @Transactional
    public Bank create(Bank bank) {
        return bankDAO.save(bank);
    }

    @Override
    public List<Bank> getAll() {
        return bankDAO.findAll();
    }

    @Override
    public Bank getByUuid(UUID uuid) {
        return bankDAO.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(Bank bank) {
        bankDAO.delete(bank);
    }
}
