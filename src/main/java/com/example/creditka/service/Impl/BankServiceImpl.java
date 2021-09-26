package com.example.creditka.service.Impl;

import com.example.creditka.DAO.Impl.BankDAOImpl;
import com.example.creditka.entity.Bank;
import com.example.creditka.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {
    public static final Logger LOG = LoggerFactory.getLogger(BankServiceImpl.class);

    private final BankDAOImpl bankDAO;

    @Autowired
    public BankServiceImpl(BankDAOImpl bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Override
    public Bank getBankCredits(UUID uuid) {
        return bankDAO.findBankCredits(uuid);
    }

    @Override
    public Bank getById(UUID uuid) {
        return bankDAO.getById(uuid);
    }

    @Override
    public Bank getByName(String name) {
        return bankDAO.findByNameBank(name);
    }

    @Override
    public List<Bank> getAll() {
        return bankDAO.findAll();
    }

    @Override
    public void deleteByID(UUID uuid) {
          bankDAO.deleteById(uuid);
    }

    @Override
    public void save(Bank bank) {
        Bank thisBank = bank;
        bankDAO.save(bank);
        LOG.info("Bank save to id:", thisBank.getId());
    }

    @Override
    public void update(Bank bank) {
       bankDAO.save(bank);
    }

    @Override
    public void delete(Bank bank) {
       bankDAO.delete(bank);
    }
}
