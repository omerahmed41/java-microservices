package com.hitpixel.invoice.application.controller;


import com.hitpixel.invoice.domain.entity.Invoice;
import com.hitpixel.invoice.domain.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Invoice controller.
 */
@RestController
@RequestMapping("/invoices")
@Slf4j
public class InvoiceController {


    private final InvoiceService invoiceService;


    /**
     * Instantiates a new Invoice controller.
     *
     * @param invoiceService the invoice service
     */
    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    /**
     * Gets invoice with client id.
     *
     * @param clientID the client id
     * @return the invoice with client id
     */
    @GetMapping("/client-id={id}")
    public Invoice getInvoiceWithClientID(@PathVariable("id") Long clientID) {
        log.info("Inside getUserWithDepartment of UserController");
        return invoiceService.getInvoiceWithClientID(clientID);
    }

    /**
     * Pay invoice with client id invoice.
     *
     * @param clientID the client id
     * @return the invoice
     */
    @GetMapping("/bill/client-id={id}")
    public Invoice generateInvoiceBillWithClientID(@PathVariable("id") Long clientID) {
        log.info("Inside getUserWithDepartment of UserController");
        return invoiceService.generateInvoiceBillWithClientID(clientID);
    }


    /**
     * Gets all invoices.
     *
     * @return the all invoices
     */
    @GetMapping("/")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }




}
