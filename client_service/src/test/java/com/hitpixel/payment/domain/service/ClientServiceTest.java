package com.hitpixel.payment.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hitpixel.payment.Infrastructure.repository.ClientRepository;
import com.hitpixel.payment.domain.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

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
     * Method under test: {@link ClientService#saveUser(Client)}
     */
    @Test
    void testSaveUser() {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientRepository.save((Client) any())).thenReturn(client);
        doNothing().when(messagePublisher).publishUserCreatedMessage((Client) any());

        Client client1 = new Client();
        client1.setBilling_interval("Billing interval");
        client1.setClient("Client");
        client1.setCredit(1L);
        client1.setEmail("jane.doe@example.org");
        client1.setFees(1L);
        client1.setFees_type("Fees type");
        client1.setUserId(123L);
        assertSame(client, clientService.saveUser(client1));
        verify(clientRepository).save((Client) any());
        verify(messagePublisher).publishUserCreatedMessage((Client) any());
    }

    /**
     * Method under test: {@link ClientService#getAllClient()}
     */
    @Test
    void testGetAllClient() {
        when(clientRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Client>> actualAllClient = clientService.getAllClient();
        assertTrue(actualAllClient.hasBody());
        assertEquals(HttpStatus.OK, actualAllClient.getStatusCode());
        assertTrue(actualAllClient.getHeaders().isEmpty());
        verify(clientRepository).findAll();
    }

    /**
     * Method under test: {@link ClientService#getClient(Long)}
     */
    @Test
    void testGetClient() {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientRepository.findByUserId((Long) any())).thenReturn(client);
        assertSame(client, clientService.getClient(123L));
        verify(clientRepository).findByUserId((Long) any());
    }

    /**
     * Method under test: {@link ClientService#chargeClient(Long, Long)}
     */
    @Test
    void testChargeClient() {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);

        Client client1 = new Client();
        client1.setBilling_interval("Billing interval");
        client1.setClient("Client");
        client1.setCredit(1L);
        client1.setEmail("jane.doe@example.org");
        client1.setFees(1L);
        client1.setFees_type("Fees type");
        client1.setUserId(123L);
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findByUserId((Long) any())).thenReturn(client);
        doNothing().when(messagePublisher).publishClientChargedMessage((Client) any());
        assertSame(client1, clientService.chargeClient(123L, 10L));
        verify(clientRepository).findByUserId((Long) any());
        verify(clientRepository).save((Client) any());
        verify(messagePublisher).publishClientChargedMessage((Client) any());
    }

    /**
     * Method under test: {@link ClientService#deleteClient(long)}
     */
    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).deleteById((Long) any());
        assertEquals("Client removed !! 123", clientService.deleteClient(123L));
        verify(clientRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(long, Client)}
     */
    @Test
    void testUpdateClient() {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        Optional<Client> ofResult = Optional.of(client);

        Client client1 = new Client();
        client1.setBilling_interval("Billing interval");
        client1.setClient("Client");
        client1.setCredit(1L);
        client1.setEmail("jane.doe@example.org");
        client1.setFees(1L);
        client1.setFees_type("Fees type");
        client1.setUserId(123L);
        when(clientRepository.save((Client) any())).thenReturn(client1);
        when(clientRepository.findById((Long) any())).thenReturn(ofResult);

        Client client2 = new Client();
        client2.setBilling_interval("Billing interval");
        client2.setClient("Client");
        client2.setCredit(1L);
        client2.setEmail("jane.doe@example.org");
        client2.setFees(1L);
        client2.setFees_type("Fees type");
        client2.setUserId(123L);
        assertSame(client1, clientService.updateClient(123L, client2));
        verify(clientRepository).save((Client) any());
        verify(clientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ClientService#updateClient(long, Client)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateClient2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:377)
        //       at com.hitpixel.payment.domain.service.ClientService.updateClient(ClientService.java:67)
        //   In order to prevent updateClient(long, Client)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateClient(long, Client).
        //   See https://diff.blue/R013 to resolve this issue.

        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientRepository.save((Client) any())).thenReturn(client);
        when(clientRepository.findById((Long) any())).thenReturn(Optional.empty());

        Client client1 = new Client();
        client1.setBilling_interval("Billing interval");
        client1.setClient("Client");
        client1.setCredit(1L);
        client1.setEmail("jane.doe@example.org");
        client1.setFees(1L);
        client1.setFees_type("Fees type");
        client1.setUserId(123L);
        clientService.updateClient(123L, client1);
    }
}

