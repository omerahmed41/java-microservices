package com.hitpixel.payment.domain.service;


import com.hitpixel.payment.Infrastructure.repository.ClientRepository;
import com.hitpixel.payment.domain.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final MessagePublisher messagePublisher;


    @Autowired
    public ClientService(MessagePublisher messagePublisher, ClientRepository clientRepository) {
        this.messagePublisher = messagePublisher;
        this.clientRepository = clientRepository;
    }


    public Client saveUser(Client client) {
        log.info("Inside saveUser of UserService");
        client = clientRepository.save(client);
        this.messagePublisher.publishUserCreatedMessage(client);
        return client;
    }

    public ResponseEntity<List<Client>> getAllClient() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    public Client getClient(Long clientId) {
        log.info("getClient:" + clientId);
        return clientRepository.findByUserId(clientId);
    }

    public Client chargeClient(Long clientId, Long amount) {
        log.info("chargeClient:" + clientId);
        Client client = this.getClient(clientId);
        client.setCredit(client.getCredit() - amount);
        client = clientRepository.save(client);
        this.messagePublisher.publishClientChargedMessage(client);
        return client;

    }

    public String deleteClient(long id) {
        clientRepository.deleteById(id);
        return "Client removed !! " + id;
    }

    public Client updateClient(long id, Client client) {
        Client existingClient = clientRepository.findById(id).orElseThrow();
        existingClient.setEmail(client.getEmail());
        existingClient.setFees(client.getFees());
        return clientRepository.save(existingClient);
    }
}
