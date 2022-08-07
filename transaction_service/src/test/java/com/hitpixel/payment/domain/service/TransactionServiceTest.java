package com.hitpixel.payment.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hitpixel.payment.Infrastructure.repository.TransactionRepository;
import com.hitpixel.payment.domain.entity.Transaction;

import java.time.LocalDateTime;

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

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @MockBean
    private MessagePublisher messagePublisher;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionService#saveTransaction(Transaction)}
     */
    @Test
    void testSaveTransaction() {
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
        when(transactionRepository.save((Transaction) any())).thenReturn(transaction);
        doNothing().when(messagePublisher).publishTransactionCreatedMessage((Transaction) any());

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
        assertSame(transaction, transactionService.saveTransaction(transaction1));
        verify(transactionRepository).save((Transaction) any());
        verify(messagePublisher).publishTransactionCreatedMessage((Transaction) any());
    }

    /**
     * Method under test: {@link TransactionService#getAllTransaction()}
     */
    @Test
    void testGetAllTransaction() {
        when(transactionRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Transaction>> actualAllTransaction = transactionService.getAllTransaction();
        assertTrue(actualAllTransaction.hasBody());
        assertEquals(HttpStatus.OK, actualAllTransaction.getStatusCode());
        assertTrue(actualAllTransaction.getHeaders().isEmpty());
        verify(transactionRepository).findAll();
    }

    /**
     * Method under test: {@link TransactionService#getTransaction(Long)}
     */
    @Test
    void testGetTransaction() {
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
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(transaction, transactionService.getTransaction(1L));
        verify(transactionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TransactionService#getTransaction(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTransaction2() {
        // TODO: Complete this test.

        when(transactionRepository.findById((Long) any())).thenReturn(Optional.empty());
        transactionService.getTransaction(1L);
    }

    /**
     * Method under test: {@link TransactionService#deleteTransaction(long)}
     */
    @Test
    void testDeleteTransaction() {
        doNothing().when(transactionRepository).deleteById((Long) any());
        assertEquals("Transaction removed !! 123", transactionService.deleteTransaction(123L));
        verify(transactionRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link TransactionService#updateTransaction(long, Transaction)}
     */
    @Test
    void testUpdateTransaction() {
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
        Optional<Transaction> ofResult = Optional.of(transaction);

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
        when(transactionRepository.save((Transaction) any())).thenReturn(transaction1);
        when(transactionRepository.findById((Long) any())).thenReturn(ofResult);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(10L);
        transaction2.setCard_type("Card type");
        transaction2.setClientID(1L);
        transaction2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction2.setCurrency("GBP");
        transaction2.setEmail("jane.doe@example.org");
        transaction2.setOrderID(1L);
        transaction2.setStatus("Status");
        transaction2.setTransactionID(1L);
        assertSame(transaction1, transactionService.updateTransaction(123L, transaction2));
        verify(transactionRepository).save((Transaction) any());
        verify(transactionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TransactionService#updateTransaction(long, Transaction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateTransaction2() {
        // TODO: Complete this test.


    }

    /**
     * Method under test: {@link TransactionService#refundRequest(Long)}
     */
    @Test
    void testRefundRequest() {
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
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(transaction, transactionService.refundRequest(1L));
        verify(transactionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TransactionService#refundRequest(Long)}
     */
    @Test
    void testRefundRequest2() {
        Transaction transaction = new Transaction();
        transaction.setAmount(10L);
        transaction.setCard_type("Card type");
        transaction.setClientID(1L);
        transaction.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction.setCurrency("GBP");
        transaction.setEmail("jane.doe@example.org");
        transaction.setOrderID(1L);
        transaction.setStatus("approved");
        transaction.setTransactionID(1L);
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(messagePublisher).publishTransactionRefundRequestedMessage((Transaction) any());
        assertSame(transaction, transactionService.refundRequest(1L));
        verify(transactionRepository).findById((Long) any());
        verify(messagePublisher).publishTransactionRefundRequestedMessage((Transaction) any());
    }

    /**
     * Method under test: {@link TransactionService#refundRequest(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefundRequest3() {


        when(transactionRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(messagePublisher).publishTransactionRefundRequestedMessage((Transaction) any());
        transactionService.refundRequest(1L);
    }

    /**
     * Method under test: {@link TransactionService#refundDone(Long)}
     */
    @Test
    void testRefundDone() {
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
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(transaction, transactionService.refundDone(1L));
        verify(transactionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TransactionService#refundDone(Long)}
     */
    @Test
    void testRefundDone2() {
        Transaction transaction = new Transaction();
        transaction.setAmount(10L);
        transaction.setCard_type("Card type");
        transaction.setClientID(1L);
        transaction.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        transaction.setCurrency("GBP");
        transaction.setEmail("jane.doe@example.org");
        transaction.setOrderID(1L);
        transaction.setStatus("approved");
        transaction.setTransactionID(1L);
        Optional<Transaction> ofResult = Optional.of(transaction);

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
        when(transactionRepository.save((Transaction) any())).thenReturn(transaction1);
        when(transactionRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(messagePublisher).publishTransactionRefundDoneMessage((Transaction) any());
        Transaction actualRefundDoneResult = transactionService.refundDone(1L);
        assertSame(transaction, actualRefundDoneResult);
        assertEquals("refunded", actualRefundDoneResult.getStatus());
        verify(transactionRepository).save((Transaction) any());
        verify(transactionRepository).findById((Long) any());
        verify(messagePublisher).publishTransactionRefundDoneMessage((Transaction) any());
    }

    /**
     * Method under test: {@link TransactionService#refundDone(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefundDone3() {

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
        when(transactionRepository.save((Transaction) any())).thenReturn(transaction);
        when(transactionRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(messagePublisher).publishTransactionRefundDoneMessage((Transaction) any());
        transactionService.refundDone(1L);
    }
}

