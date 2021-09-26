package com.example.creditka.DAO.Impl;

import com.example.creditka.DAO.BankDAO;
import com.example.creditka.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface BankDAOImpl extends JpaRepository<Bank, UUID>, BankDAO {
    @Override
    Bank findByNameBank(String name);

    @Override
    @Query("SELECT c FROM Bank c join fetch c.clients where c.id=:uuid")
    Bank findBankClients(@Param("uuid") UUID uuid);

    @Override
    @Query("SELECT a FROM Bank a join fetch a.credits where a.id=:uuid")
    Bank findBankCredits(@Param("uuid") UUID uuid);
}
