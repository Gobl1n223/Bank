package com.example.creditka.DAO;

import com.example.creditka.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditDAO extends JpaRepository<Credit, UUID> {
    List<Credit> findByBankID(UUID uuid);
}
