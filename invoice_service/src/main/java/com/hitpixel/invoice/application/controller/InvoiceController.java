package com.hitpixel.invoice.application.controller;


import com.hitpixel.invoice.domain.VO.ResponseTemplateVO;
import com.hitpixel.invoice.domain.entity.Invoice;
import com.hitpixel.invoice.domain.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@Slf4j
public class InvoiceController {


    private final InvoiceService invoiceService;



    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping("/client-id={id}")
    public Invoice getInvoiceWithClientID(@PathVariable("id") Long clientID) {
        log.info("Inside getUserWithDepartment of UserController");
        return invoiceService.getInvoiceWithClientID(clientID);
    }

    @GetMapping("/pay/client-id={id}")
    public Invoice payInvoiceWithClientID(@PathVariable("id") Long clientID) {
        log.info("Inside getUserWithDepartment of UserController");
        return invoiceService.payInvoiceWithClientID(clientID);
    }




    @GetMapping("/")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }


}
