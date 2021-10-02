package com.example.creditka.service.impl;

import com.example.creditka.entity.Credit;
import com.example.creditka.DAO.CreditDAO;
import com.example.creditka.service.IService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements IService<Credit> {
    private final CreditDAO creditDAO;

    public CreditServiceImpl(CreditDAO creditDAO) {
        this.creditDAO = creditDAO;
    }
    @Override
    @Transactional
    public Credit create(Credit credit) {
        return creditDAO.save(credit);
    }

    @Override
    public List<Credit> getAll() {
        return creditDAO.findAll();
    }

    @Override
    public Credit getByUuid(UUID uuid) {
        return creditDAO.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(Credit credit) {
        creditDAO.delete(credit);
    }
}
