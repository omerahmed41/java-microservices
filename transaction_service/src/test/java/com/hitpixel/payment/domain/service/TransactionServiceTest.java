package com.hitpixel.payment.domain.service;

import com.hitpixel.payment.Infrastructure.repository.TransactionRepository;
import com.hitpixel.payment.domain.entity.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Should return transaction when transaction status is approved")
    void refundDoneWhenTransactionStatusIsApprovedThenReturnTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(1L);
        transaction.setStatus("approved");
        when(transactionRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(transaction));
        Transaction refundedTransaction = transactionService.refundDone(1L);
        assertEquals("refunded", refundedTransaction.getStatus());
    }

    @Test
    @DisplayName("Should return transaction when transaction status is not approved")
    void refundDoneWhenTransactionStatusIsNotApprovedThenReturnTransaction() {
        Transaction transaction = new Transaction();
        transaction.setStatus("not_approved");
        when(transactionRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(transaction));
        Transaction result = transactionService.refundDone(1L);
        assertEquals(transaction, result);
    }

    @Test
    @DisplayName("Should throw exception when transaction is not found")
    void refundRequestWhenTransactionIsNotFoundThenThrowException() {
        when(transactionRepository.findById(anyLong())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> transactionService.refundRequest(1L));
    }

    @Test
    @DisplayName("Should delete the transaction when the id is valid")
    void deleteTransactionWhenIdIsValid() {
        long id = 1L;
        Transaction transaction = new Transaction();
        transaction.setTransactionID(id);
        transaction.setEmail("test@test.com");
        transaction.setAmount(100L);
        transaction.setCurrency("USD");
        transaction.setStatus("approved");
        transaction.setCreatedAt(LocalDateTime.now());

        when(transactionRepository.findById(id)).thenReturn(java.util.Optional.of(transaction));

        String result = transactionService.deleteTransaction(id);

        assertEquals("Transaction removed !! 1", result);
    }

    @Test
    @DisplayName("Should return the transaction when the transaction exists")
    void getTransactionWhenTransactionExists() {
        Transaction transaction =
                new Transaction(
                        1L,
                        1L,
                        1L,
                        "test@test.com",
                        LocalDateTime.now(),
                        100L,
                        "USD",
                        "VISA",
                        "approved");
        when(transactionRepository.findById(1L)).thenReturn(java.util.Optional.of(transaction));
        Transaction result = transactionService.getTransaction(1L);
        assertSame(transaction, result);
    }

    @Test
    @DisplayName("Should throw an exception when the transaction does not exist")
    void getTransactionWhenTransactionDoesNotExistThenThrowsException() {
        when(transactionRepository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> transactionService.getTransaction(1L));
    }

    @Test
    @DisplayName("Should return all transactions")
    void getAllTransactionShouldReturnAllTransactions() {
        Transaction transaction =
                new Transaction(
                        1L,
                        1L,
                        1L,
                        "test@test.com",
                        LocalDateTime.now(),
                        100L,
                        "USD",
                        "VISA",
                        "approved");
        when(transactionRepository.findAll()).thenReturn(List.of(transaction));
        assertSame(transactionService.getAllTransaction().getBody().get(0), transaction);
    }

    @Test
    @DisplayName("Should save the transaction")
    void saveTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(1L);
        transaction.setOrderID(1L);
        transaction.setClientID(1L);
        transaction.setEmail("test@test.com");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAmount(100L);
        transaction.setCurrency("USD");
        transaction.setCard_type("VISA");
        transaction.setStatus("approved");

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertEquals(savedTransaction, transaction);
    }

    @Test
    @DisplayName("Should publish a message")
    void saveTransactionShouldPublishMessage() {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(1L);
        transaction.setEmail("test@test.com");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAmount(100L);
        transaction.setCurrency("EUR");
        transaction.setCard_type("VISA");
        transaction.setStatus("approved");

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        doNothing().when(messagePublisher).publishTransactionCreatedMessage(transaction);

        Transaction saved_transaction = transactionService.saveTransaction(transaction);

        assertSame(saved_transaction, transaction);

        verify(messagePublisher, times(1)).publishTransactionCreatedMessage(transaction);
    }
}

