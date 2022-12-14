package com.hitpixel.payment.domain.service;



import com.hitpixel.payment.Infrastructure.repository.TransactionRepository;
import com.hitpixel.payment.domain.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Objects;

/**
 * The type Transaction service.
 */
@Service
@Slf4j
public class TransactionService {

    private final MessagePublisher messagePublisher;


    /**
     * Instantiates a new Transaction service.
     *
     * @param messagePublisher the message publisher
     */
    @Autowired
    public TransactionService(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Save transaction transaction.
     *
     * @param transaction the transaction
     * @return the transaction
     */
    public Transaction saveTransaction(Transaction transaction) {
        log.info("Inside saveUser of UserService");
        Transaction saved_transaction =  transactionRepository.save(transaction);
        this.messagePublisher.publishTransactionCreatedMessage(saved_transaction);
        return saved_transaction;
    }

    /**
     * Gets all transaction.
     *
     * @return the all transaction
     */
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    /**
     * Gets transaction.
     *
     * @param transactionID the transaction id
     * @return the transaction
     */
    public Transaction getTransaction(Long transactionID) {
        log.info("Inside getUserWithDepartment of UserService");
        return transactionRepository.findById(transactionID).orElseThrow();

    }

    /**
     * Delete transaction string.
     *
     * @param id the id
     * @return the string
     */
    public String deleteTransaction(long id) {
        transactionRepository.deleteById(id);
        return "Transaction removed !! " + id;
    }

    /**
     * Update transaction transaction.
     *
     * @param id          the id
     * @param transaction the transaction
     * @return the transaction
     */
    public Transaction updateTransaction(long id,Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id).orElseThrow();
        existingTransaction.setEmail(transaction.getEmail());
        existingTransaction.setStatus(transaction.getStatus());
        return transactionRepository.save(existingTransaction);
    }

    /**
     * Refund request transaction.
     *
     * @param transactionID the transaction id
     * @return the transaction
     */
    public Transaction refundRequest(Long transactionID) {
        Transaction transaction = this.getTransaction(transactionID);
        if (Objects.equals(transaction.getStatus(), "approved")){
            this.messagePublisher.publishTransactionRefundRequestedMessage(transaction);
        }

        return  transaction;
    }

    /**
     * Refund done transaction.
     *
     * @param transactionID the transaction id
     * @return the transaction
     */
    public Transaction refundDone(Long transactionID) {
        Transaction transaction = this.getTransaction(transactionID);
        if (Objects.equals(transaction.getStatus(), "approved")){
            transaction.setStatus("refunded");
            transactionRepository.save(transaction);
            this.messagePublisher.publishTransactionRefundDoneMessage(transaction);
        }

        return  transaction;
    }
}
