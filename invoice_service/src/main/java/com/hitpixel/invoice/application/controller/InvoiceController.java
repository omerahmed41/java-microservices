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


    @GetMapping("/user-id={id}")
    public ResponseTemplateVO getInvoiceWithUserID(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return invoiceService.getInvoiceWithUserID(userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }


}
