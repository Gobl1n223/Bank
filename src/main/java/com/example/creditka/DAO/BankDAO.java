package com.example.creditka.DAO;

import com.example.creditka.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface BankDAO extends JpaRepository<Bank, UUID> {
    Bank findByUuid(UUID uuid);
}
