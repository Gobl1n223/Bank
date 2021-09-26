package com.example.creditka.service.Impl;


import com.example.creditka.DAO.Impl.CreditOfferDAOImpl;
import com.example.creditka.entity.CreditOffer;
import com.example.creditka.service.CreditOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferServiceImpl implements CreditOfferService {


    private final CreditOfferDAOImpl creditOfferDAO;

    @Autowired
    public CreditOfferServiceImpl(CreditOfferDAOImpl creditOfferDAO) {
        this.creditOfferDAO = creditOfferDAO;
    }

    @Override
    public List<CreditOffer> getAll() {
        return creditOfferDAO.findAll();
    }

    @Override
    public CreditOffer getOne(UUID uuid) {
        return creditOfferDAO.getById(uuid);
    }

    @Override
    public List<CreditOffer> getByCreditID(UUID uuid) {
        return creditOfferDAO.findByCreditID(uuid);
    }

    @Override
    public List<CreditOffer> getByClientID(UUID uuid) {
        return creditOfferDAO.findByClientID(uuid);
    }

    @Override
    public void save(CreditOffer creditOffer) {
       creditOfferDAO.save(creditOffer);
    }

    @Override
    public void update(CreditOffer creditOffer) {
        creditOfferDAO.save(creditOffer);
    }

    @Override
    public void deleteById(UUID uuid) {
      creditOfferDAO.deleteById(uuid);
    }

    @Override
    public void delete(CreditOffer creditOffer) {
         creditOfferDAO.delete(creditOffer);
    }
}
