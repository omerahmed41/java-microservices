package com.hitpixel.payment.domain.service;

import com.hitpixel.payment.Infrastructure.repository.ClientRepository;
import com.hitpixel.payment.domain.entity.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * The type Client service test.
 */
@ContextConfiguration(classes = {ClientService.class})
@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @MockBean
    private MessagePublisher messagePublisher;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Update client when client is found then return client.
     */
    @Test
    @DisplayName("Should return client when client is found")
    void updateClientWhenClientIsFoundThenReturnClient() {
        Client client = new Client();
        client.setUserId(1L);
        client.setEmail("test@test.com");
        client.setFees(100L);
        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client updatedClient = clientService.updateClient(1L, client);

        assertEquals("test@test.com", updatedClient.getEmail());
        assertEquals(100L, updatedClient.getFees());
    }

    /**
     * Update client when client is not found then throw exception.
     */
    @Test
    @DisplayName("Should throw exception when client is not found")
    void updateClientWhenClientIsNotFoundThenThrowException() {
        Client client = new Client();
        client.setUserId(1L);
        client.setEmail("test@test.com");
        client.setFees(100L);

        when(clientRepository.findById(1L)).thenReturn(null);

        assertThrows(
                RuntimeException.class,
                () -> {
                    clientService.updateClient(1L, client);
                });
    }

    /**
     * Delete client when id is valid.
     */
    @Test
    @DisplayName("Should delete the client when the id is valid")
    void deleteClientWhenIdIsValid() {
        long id = 1;
        Client client = new Client();
        client.setUserId(id);
        client.setEmail("test@test.com");
        client.setCredit(100L);
        client.setFees(10L);

        when(clientRepository.findById(id)).thenReturn(java.util.Optional.of(client));

        String result = clientService.deleteClient(id);

        assertEquals("Client removed !! 1", result);
    }

    /**
     * Charge client when client is not found then throw exception.
     */
    @Test
    @DisplayName("Should throw exception when client is not found")
    void chargeClientWhenClientIsNotFoundThenThrowException() {
        Long clientId = 1L;
        Long amount = 100L;
        when(clientRepository.findByUserId(clientId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> clientService.chargeClient(clientId, amount));

        verify(clientRepository, times(1)).findByUserId(clientId);
    }

    /**
     * Charge client when client is found then return result.
     */
    @Test
    @DisplayName("Should return client when client is found")
    void chargeClientWhenClientIsFoundThenReturnResult() {
        Client client = new Client();
        client.setUserId(1L);
        client.setCredit(100L);
        when(clientRepository.findByUserId(1L)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        Client result = clientService.chargeClient(1L, 10L);
        assertEquals(90L, result.getCredit());
    }

    /**
     * Gets client when client exists.
     */
    @Test
    @DisplayName("Should return the client when the client exists")
    void getClientWhenClientExists() {
        Client client = new Client();
        client.setUserId(1L);
        client.setClient("client");
        client.setEmail("email");
        client.setBilling_interval("billing_interval");
        client.setFees_type("fees_type");
        client.setFees(1L);
        client.setCredit(100L);

        when(clientRepository.findByUserId(1L)).thenReturn(client);

        Client result = clientService.getClient(1L);

        assertEquals(client, result);
    }

    /**
     * Gets all client should returns a list of clients.
     */
    @Test
    @DisplayName("Should returns a list of clients")
    void getAllClientShouldReturnsAListOfClients() {
        Client client = new Client();
        client.setUserId(1L);
        client.setClient("client");
        client.setEmail("email");
        client.setBilling_interval("billing_interval");
        client.setFees_type("fees_type");
        client.setFees(1L);
        client.setCredit(1L);

        when(clientRepository.findAll()).thenReturn(List.of(client));

        assertEquals(List.of(client), clientService.getAllClient().getBody());
    }

    /**
     * Save user when user does not exist.
     */
    @Test
    @DisplayName("Should save the user when the user does not exist")
    void saveUserWhenUserDoesNotExist() {
        Client client = new Client();
        client.setUserId(1L);
        client.setClient("test");
        client.setEmail("test@gmail.com");
        client.setBilling_interval("monthly");
        client.setFees_type("fixed");
        client.setFees(100L);
        client.setCredit(100L);

        when(clientRepository.save(client)).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertEquals(savedClient, client);
    }
}

