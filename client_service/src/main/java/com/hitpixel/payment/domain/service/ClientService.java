package com.hitpixel.payment.domain.service;


import com.hitpixel.payment.Infrastructure.repository.ClientRepository;
import com.hitpixel.payment.domain.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Client service.
 */
@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final MessagePublisher messagePublisher;


    /**
     * Instantiates a new Client service.
     *
     * @param messagePublisher the message publisher
     * @param clientRepository the client repository
     */
    @Autowired
    public ClientService(MessagePublisher messagePublisher, ClientRepository clientRepository) {
        this.messagePublisher = messagePublisher;
        this.clientRepository = clientRepository;
    }


    /**
     * Save user client.
     *
     * @param client the client
     * @return the client
     */
    public Client saveUser(Client client) {
        log.info("Inside saveUser of UserService");
        client = clientRepository.save(client);
        this.messagePublisher.publishUserCreatedMessage(client);
        return client;
    }

    /**
     * Gets all client.
     *
     * @return the all client
     */
    public ResponseEntity<List<Client>> getAllClient() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    /**
     * Gets client.
     *
     * @param clientId the client id
     * @return the client
     */
    public Client getClient(Long clientId) {
        log.info("getClient:" + clientId);
        return clientRepository.findByUserId(clientId);
    }

    /**
     * Charge client.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return the client
     */
    public Client chargeClient(Long clientId, Long amount) {
        log.info("chargeClient:" + clientId);
        Client client = this.getClient(clientId);
        client.setCredit(client.getCredit() - amount);
        client = clientRepository.save(client);
        this.messagePublisher.publishClientChargedMessage(client);
        return client;

    }

    /**
     * Delete client.
     *
     * @param id the id
     * @return the string
     */
    public String deleteClient(long id) {
        clientRepository.deleteById(id);
        return "Client removed !! " + id;
    }

    /**
     * Update client.
     *
     * @param id     the id
     * @param client the client
     * @return the client
     */
    public Client updateClient(long id, Client client) {
        Client existingClient = clientRepository.findById(id).orElseThrow();
        existingClient.setEmail(client.getEmail());
        existingClient.setFees(client.getFees());
        return clientRepository.save(existingClient);
    }
}
