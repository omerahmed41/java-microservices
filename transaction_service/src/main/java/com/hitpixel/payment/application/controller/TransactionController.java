package com.hitpixel.payment.application.controller;


import com.hitpixel.payment.domain.entity.Transaction;
import com.hitpixel.payment.domain.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Transaction controller.
 */
@RestController
@RequestMapping("/Transactions")
@Slf4j
public class TransactionController {


    private final TransactionService transactionService;


    /**
     * Instantiates a new Transaction controller.
     *
     * @param transactionService the transaction service
     */
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Save transaction.
     *
     * @param transaction the transaction
     * @return the transaction
     */
    @PostMapping("/")
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        log.info("saveTransaction" + transaction );

        return transactionService.saveTransaction(transaction);
    }

    /**
     * Gets transaction.
     *
     * @param transactionID the transaction id
     * @return the user with department
     */
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") Long transactionID) {
        log.info("getTransaction");
        return transactionService.getTransaction(transactionID);
    }

    /**
     * Gets all transaction.
     *
     * @return the all transaction
     */
    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    /**
     * Update transaction transaction.
     *
     * @param id     the id
     * @param client the client
     * @return the transaction
     */
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable int id, @RequestBody Transaction client) {
        return transactionService.updateTransaction(id, client);
    }

    /**
     * Delete transaction string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

    /**
     * Refund transactions transaction.
     *
     * @param transactionID the transaction id
     * @return the transaction
     */
    @PostMapping("refund/{id}")
    public Transaction refundTransactions(@PathVariable("id") Long transactionID) {
        log.info("refundTransactions" + transactionID);
        return transactionService.refundRequest(transactionID);
    }

}
