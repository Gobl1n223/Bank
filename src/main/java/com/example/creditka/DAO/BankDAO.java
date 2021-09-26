package com.example.creditka.DAO;

import com.example.creditka.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankDAO extends JpaRepository<Bank, UUID> {
    Bank findByNameBank(String name);
    Bank findBankClients(UUID uuid);
    Bank findBankCredits(UUID uuid);
}
