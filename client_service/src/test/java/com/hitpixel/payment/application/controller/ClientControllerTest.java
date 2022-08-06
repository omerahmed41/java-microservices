package com.hitpixel.payment.application.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitpixel.payment.domain.entity.Client;
import com.hitpixel.payment.domain.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClientController.class})
@ExtendWith(SpringExtension.class)
class ClientControllerTest {
    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientService clientService;

    /**
     * Method under test: {@link ClientController#saveUser(Client)}
     */
    @Test
    void testSaveUser() throws Exception {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientService.saveUser((Client) any())).thenReturn(client);

        Client client1 = new Client();
        client1.setBilling_interval("Billing interval");
        client1.setClient("Client");
        client1.setCredit(1L);
        client1.setEmail("jane.doe@example.org");
        client1.setFees(1L);
        client1.setFees_type("Fees type");
        client1.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(client1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"client\":\"Client\",\"email\":\"jane.doe@example.org\",\"billing_interval\":\"Billing interval\""
                                        + ",\"fees_type\":\"Fees type\",\"fees\":1,\"credit\":1}"));
    }

    /**
     * Method under test: {@link ClientController#getClient(Long)}
     */
    @Test
    void testGetClient() throws Exception {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientService.getClient((Long) any())).thenReturn(client);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/{id}", 123L);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"client\":\"Client\",\"email\":\"jane.doe@example.org\",\"billing_interval\":\"Billing interval\""
                                        + ",\"fees_type\":\"Fees type\",\"fees\":1,\"credit\":1}"));
    }

    /**
     * Method under test: {@link ClientController#updateClient(int, Client)}
     */
    @Test
    void testUpdateClient() throws Exception {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientService.updateClient(anyLong(), (Client) any())).thenReturn(client);

        Client client1 = new Client();
        client1.setBilling_interval("Billing interval");
        client1.setClient("Client");
        client1.setCredit(1L);
        client1.setEmail("jane.doe@example.org");
        client1.setFees(1L);
        client1.setFees_type("Fees type");
        client1.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(client1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clients/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"client\":\"Client\",\"email\":\"jane.doe@example.org\",\"billing_interval\":\"Billing interval\""
                                        + ",\"fees_type\":\"Fees type\",\"fees\":1,\"credit\":1}"));
    }

    /**
     * Method under test: {@link ClientController#clientTransactions(Long)}
     */
    @Test
    void testClientTransactions() throws Exception {
        Client client = new Client();
        client.setBilling_interval("Billing interval");
        client.setClient("Client");
        client.setCredit(1L);
        client.setEmail("jane.doe@example.org");
        client.setFees(1L);
        client.setFees_type("Fees type");
        client.setUserId(123L);
        when(clientService.getClient((Long) any())).thenReturn(client);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/client-transactions/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"client\":\"Client\",\"email\":\"jane.doe@example.org\",\"billing_interval\":\"Billing interval\""
                                        + ",\"fees_type\":\"Fees type\",\"fees\":1,\"credit\":1}"));
    }

    /**
     * Method under test: {@link ClientController#deleteClient(int)}
     */
    @Test
    void testDeleteClient() throws Exception {
        when(clientService.deleteClient(anyLong())).thenReturn("Delete Client");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/clients/{id}", 1);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Client"));
    }

    /**
     * Method under test: {@link ClientController#deleteClient(int)}
     */
    @Test
    void testDeleteClient2() throws Exception {
        when(clientService.deleteClient(anyLong())).thenReturn("Delete Client");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/clients/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Client"));
    }

    /**
     * Method under test: {@link ClientController#getAllClients()}
     */
    @Test
    void testGetAllClients() throws Exception {
        when(clientService.getAllClient()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}
