package com.example.creditka.DAO.Impl;

import com.example.creditka.DAO.CreditOfferDAO;
import com.example.creditka.entity.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface CreditOfferDAOImpl extends JpaRepository<CreditOffer, UUID>, CreditOfferDAO {
    @Override
    List<CreditOffer> findByClientID(UUID uuid);

    @Override
    List<CreditOffer> findByCreditID(UUID uuid);
}
