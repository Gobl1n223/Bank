package com.example.creditka.DAO;

import com.example.creditka.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface ClientDAO extends JpaRepository<Client, UUID> {
    Client findByUuid(UUID uuid);
}
