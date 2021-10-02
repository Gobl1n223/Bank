package com.example.creditka.DAO;

import com.example.creditka.entity.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface CreditOfferDAO extends JpaRepository<CreditOffer, UUID> {
    CreditOffer findByUuid(UUID uuid);
}
