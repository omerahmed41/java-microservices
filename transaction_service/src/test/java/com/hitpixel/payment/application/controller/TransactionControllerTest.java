package com.hitpixel.payment.application.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitpixel.payment.domain.entity.Transaction;
import com.hitpixel.payment.domain.service.TransactionService;

import java.time.LocalDateTime;

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

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionController#getAllTransaction()}
     */
    @Test
    void testGetAllTransaction() throws Exception {
        when(transactionService.getAllTransaction()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Transactions/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link TransactionController#updateTransaction(int, Transaction)}
     */
    @Test
    void testUpdateTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(10L);
        transaction.setCard_type("Card type");
        transaction.setClientID(1L);
        transaction.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction.setCurrency("GBP");
        transaction.setEmail("jane.doe@example.org");
        transaction.setOrderID(1L);
        transaction.setStatus("Status");
        transaction.setTransactionID(1L);
        when(transactionService.updateTransaction(anyLong(), (Transaction) any())).thenReturn(transaction);

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(10L);
        transaction1.setCard_type("Card type");
        transaction1.setClientID(1L);
        transaction1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction1.setCurrency("GBP");
        transaction1.setEmail("jane.doe@example.org");
        transaction1.setOrderID(1L);
        transaction1.setStatus("Status");
        transaction1.setTransactionID(1L);
        String content = (new ObjectMapper()).writeValueAsString(transaction1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/Transactions/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"transactionID\":1,\"orderID\":1,\"clientID\":1,\"email\":\"jane.doe@example.org\",\"createdAt\":[1,1,1,1,1],"
                                        + "\"amount\":10,\"currency\":\"GBP\",\"card_type\":\"Card type\",\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link TransactionController#deleteTransaction(int)}
     */
    @Test
    void testDeleteTransaction() throws Exception {
        when(transactionService.deleteTransaction(anyLong())).thenReturn("Delete Transaction");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/Transactions/{id}", 1);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Transaction"));
    }

    /**
     * Method under test: {@link TransactionController#getTransaction(Long)}
     */
    @Test
    void testGetUserWithDepartment() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(10L);
        transaction.setCard_type("Card type");
        transaction.setClientID(1L);
        transaction.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction.setCurrency("GBP");
        transaction.setEmail("jane.doe@example.org");
        transaction.setOrderID(1L);
        transaction.setStatus("Status");
        transaction.setTransactionID(1L);
        when(transactionService.getTransaction((Long) any())).thenReturn(transaction);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Transactions/{id}", 123L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"transactionID\":1,\"orderID\":1,\"clientID\":1,\"email\":\"jane.doe@example.org\",\"createdAt\":[1,1,1,1,1],"
                                        + "\"amount\":10,\"currency\":\"GBP\",\"card_type\":\"Card type\",\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link TransactionController#refundTransactions(Long)}
     */
    @Test
    void testRefundTransactions() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(10L);
        transaction.setCard_type("Card type");
        transaction.setClientID(1L);
        transaction.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction.setCurrency("GBP");
        transaction.setEmail("jane.doe@example.org");
        transaction.setOrderID(1L);
        transaction.setStatus("Status");
        transaction.setTransactionID(1L);
        when(transactionService.refundRequest((Long) any())).thenReturn(transaction);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Transactions/refund/{id}", 123L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"transactionID\":1,\"orderID\":1,\"clientID\":1,\"email\":\"jane.doe@example.org\",\"createdAt\":[1,1,1,1,1],"
                                        + "\"amount\":10,\"currency\":\"GBP\",\"card_type\":\"Card type\",\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link TransactionController#saveTransaction(Transaction)}
     */
    @Test
    void testSaveUser() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(10L);
        transaction.setCard_type("Card type");
        transaction.setClientID(1L);
        transaction.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction.setCurrency("GBP");
        transaction.setEmail("jane.doe@example.org");
        transaction.setOrderID(1L);
        transaction.setStatus("Status");
        transaction.setTransactionID(1L);
        when(transactionService.saveTransaction((Transaction) any())).thenReturn(transaction);

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(10L);
        transaction1.setCard_type("Card type");
        transaction1.setClientID(1L);
        transaction1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction1.setCurrency("GBP");
        transaction1.setEmail("jane.doe@example.org");
        transaction1.setOrderID(1L);
        transaction1.setStatus("Status");
        transaction1.setTransactionID(1L);
        String content = (new ObjectMapper()).writeValueAsString(transaction1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Transactions/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"transactionID\":1,\"orderID\":1,\"clientID\":1,\"email\":\"jane.doe@example.org\",\"createdAt\":[1,1,1,1,1],"
                                        + "\"amount\":10,\"currency\":\"GBP\",\"card_type\":\"Card type\",\"status\":\"Status\"}"));
    }
}

