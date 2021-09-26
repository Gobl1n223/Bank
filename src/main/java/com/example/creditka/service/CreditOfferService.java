package com.example.creditka.service;

import com.example.creditka.entity.CreditOffer;

import java.util.List;
import java.util.UUID;

public interface CreditOfferService {
    List<CreditOffer> getAll();
    CreditOffer getOne(UUID uuid);
    List<CreditOffer> getByCreditID(UUID uuid);
    List<CreditOffer> getByClientID(UUID uuid);
    void save(CreditOffer creditOffer);
    void update(CreditOffer creditOffer);
    void deleteById(UUID uuid);
    void delete(CreditOffer creditOffer);

}
