package com.example.creditka.DAO;

import com.example.creditka.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface CreditDAO extends JpaRepository<Credit, UUID> {
    Credit findByUuid(UUID uuid);
}