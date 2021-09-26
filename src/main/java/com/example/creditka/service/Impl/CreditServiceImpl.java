package com.example.creditka.service.Impl;

import com.example.creditka.DAO.Impl.CreditDAOImpl;
import com.example.creditka.entity.Bank;
import com.example.creditka.entity.Credit;
import com.example.creditka.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements CreditService {
    public static final Logger LOG = LoggerFactory.getLogger(CreditServiceImpl.class);

    private final CreditDAOImpl creditDAO;

    @Autowired
    public CreditServiceImpl(CreditDAOImpl creditDAO) {
        this.creditDAO = creditDAO;
    }


    @Override
    public List<Credit> getAll() {
        return creditDAO.findAll();
    }

    @Override
    public Credit getOne(UUID uuid) {
        return creditDAO.getById(uuid);
    }

    @Override
    public List<Credit> getByBankID(UUID uuid) {
        return creditDAO.findByBankID(uuid);
    }

    @Override
    public void update(BigDecimal creditLimit, BigDecimal percentRate, Bank bank) {
        Credit credit = new Credit(bank, creditLimit, percentRate);
        creditDAO.save(credit);
        LOG.info("The loan is kept under id:", credit.getID());
    }

    @Override
    public void delete(Credit credit) {
      creditDAO.delete(credit);
    }

    @Override
    public void save(Credit credit) {
        creditDAO.save(credit);
    }

    @Override
    public void deleteById(UUID uuid) {
        creditDAO.deleteById(uuid);
    }
}
