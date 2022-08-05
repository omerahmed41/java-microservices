package com.hitpixel.payment.application.controller;


import com.hitpixel.payment.domain.VO.ResponseTemplateVO;
import com.hitpixel.payment.domain.entity.Transaction;
import com.hitpixel.payment.domain.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
@Slf4j
public class TransactionController {


    private final TransactionService transactionService;



    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/")
    public Transaction saveUser(@RequestBody Transaction transaction) {
        log.info("Inside saveUser of TransactionController");
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long transactionID) {
        log.info("Inside getUserWithDepartment of transactionController");
        return transactionService.getTransaction(transactionID);
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return transactionService.getAllTransaction();
    }
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable int id, @RequestBody Transaction client) {
        return transactionService.updateTransaction(id, client);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }



}
