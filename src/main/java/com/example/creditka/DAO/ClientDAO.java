package com.example.creditka.DAO;


import com.example.creditka.entity.Client;
import com.example.creditka.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientDAO extends JpaRepository<Client, UUID> {
    Client findCreditOffers(UUID uuid);
    Client findByPassportID(String passportID);
}
