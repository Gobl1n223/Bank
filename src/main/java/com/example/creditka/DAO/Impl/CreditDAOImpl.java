package com.example.creditka.DAO.Impl;

import com.example.creditka.DAO.CreditDAO;
import com.example.creditka.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface CreditDAOImpl extends JpaRepository<Credit, UUID>, CreditDAO {
    @Override
    List<Credit> findByBankID(UUID uuid);
}
