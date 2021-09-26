package com.example.creditka.service.Impl;

import com.example.creditka.DAO.Impl.ClientDAOImpl;
import com.example.creditka.entity.Bank;
import com.example.creditka.entity.Client;
import com.example.creditka.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    public static final Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientDAOImpl clientDAO;

    @Autowired
    public ClientServiceImpl(ClientDAOImpl clientDAO) {
        this.clientDAO = clientDAO;
    }


    @Override
    public List<Client> getAll() {
        return clientDAO.findAll();
    }

    @Override
    public Client getOne(UUID uuid) {
        return clientDAO.getById(uuid);
    }

    @Override
    public void save(String name, String phone, String passportID, Bank bank) {
        Client client = new Client(name, phone, passportID, bank);
        clientDAO.save(client);
        LOG.info("Client save to id:", client.getID());
    }

    @Override
    public void update(Client client1) {
        Client client = clientDAO.getById(client1.getID());

        client.setName(client1.getName());
        client.setPassportID(client1.getPassportID());
        client.setPhone(client1.getPhone());
        clientDAO.save(client);
        LOG.info("Client update to id:", client.getID());
    }

    @Override
    public void delete(Client client) {
        clientDAO.delete(client);
    }

    @Override
    public Client getByPassportID(String passport) {
        return clientDAO.findByPassportID(passport);
    }

    @Override
    public void deleteById(UUID uuid) {
       clientDAO.deleteById(uuid);
    }

    @Override
    public Client getCreditOffers(UUID uuid) {
        return clientDAO.findCreditOffers(uuid);
    }
}
