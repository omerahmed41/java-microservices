package com.hitpixel.payment.application.controller;


import com.hitpixel.payment.domain.entity.Client;
import com.hitpixel.payment.domain.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Client controller.
 */
@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {


    private final ClientService clientService;


    /**
     * Instantiates a new Client controller.
     *
     * @param clientService the client service
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Save user client.
     *
     * @param client the client
     * @return the client
     */
    @PostMapping("/")
    public Client saveClient(@RequestBody Client client) {
        log.info("Inside saveUser of UserController");
        return clientService.saveClient(client);
    }

    /**
     * Gets client.
     *
     * @param clientId the client id
     * @return the client
     */
    @GetMapping("/{id}")
    public Client getClient(@PathVariable("id") Long clientId) {
        log.info("Inside getUserWithDepartment of UserController");
        return clientService.getClient(clientId);
    }

    /**
     * Gets all clients.
     *
     * @return the all clients
     */
    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients() {
        return clientService.getAllClient();
    }

    /**
     * Update client.
     *
     * @param id     the id
     * @param client the client
     * @return the client
     */
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    /**
     * Delete client.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable int id) {
        return clientService.deleteClient(id);
    }

    /**
     * Client transactions.
     *
     * @param clientId the client id
     * @return the client
     */
    @GetMapping("client-transactions/{id}")
    public Client clientTransactions(@PathVariable("id") Long clientId) {
        log.info("Inside getUserWithDepartment of UserController");
        return clientService.getClientTransactions(clientId);
    }


}
