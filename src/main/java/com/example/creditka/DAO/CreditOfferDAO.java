package com.example.creditka.DAO;

import com.example.creditka.entity.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditOfferDAO extends JpaRepository<CreditOffer, UUID> {
    List<CreditOffer> findByCreditID(UUID uuid);
    List<CreditOffer> findByClientID(UUID uuid);
}
