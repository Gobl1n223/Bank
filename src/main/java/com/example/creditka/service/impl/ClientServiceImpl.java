package com.example.creditka.service.impl;

import com.example.creditka.entity.Client;
import com.example.creditka.DAO.ClientDAO;
import com.example.creditka.service.IService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements IService<Client> {

    private final ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    @Transactional
    public Client create(Client client) {
        return clientDAO.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientDAO.findAll();
    }

    @Override
    public Client getByUuid(UUID uuid) {
        return clientDAO.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        clientDAO.delete(client);
    }
}
