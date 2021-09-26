package com.example.creditka.DAO.Impl;

import com.example.creditka.DAO.ClientDAO;
import com.example.creditka.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface ClientDAOImpl extends JpaRepository<Client, UUID>, ClientDAO {
    @Override
    @Query("SELECT a FROM Client a join fetch a.creditOffers where a.ID=:uuid")
    Client findCreditOffers(@Param("uuid") UUID uuid);

    @Override
    Client findByPassportID(String passportID);
}
