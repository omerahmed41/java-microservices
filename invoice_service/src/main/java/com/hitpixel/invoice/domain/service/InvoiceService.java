package com.hitpixel.invoice.domain.service;


import com.hitpixel.invoice.Infrastructure.repository.InvoiceRepository;
import com.hitpixel.invoice.domain.entity.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final MessagePublisher messagePublisher;

    @Autowired
    public InvoiceService(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    public void createInvoice(Invoice invoice) {
        log.info("createInvoice clientID" + invoice.getClientID());
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus("Pending");
        invoiceRepository.save(invoice);
    }
    public void updateInvoice(Invoice invoice) {
        log.info("updateInvoice clientID:" + invoice.getClientID());
        invoice.setAmount(invoice.getAmount() + 1);
        invoiceRepository.save(invoice);
    }

    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceRepository.findAll());
    }

    public List<Invoice> getAllDueDateInvoices() {
        return ResponseEntity.ok(invoiceRepository.findByDueDateLessThanEqual(LocalDateTime.now())).getBody();
    }

    public Invoice getInvoiceWithClientID(Long clientID) {
        log.info("Inside getUserWithDepartment of UserService");
        return invoiceRepository.findByClientID(clientID);

    }

    public Invoice payInvoiceWithClientID(Long clientID) {
//        log.info("payInvoiceWithClientID");
        Invoice invoice =  this.getInvoiceWithClientID(clientID);
        return this.payInvoice(invoice);
    }

    public Invoice payInvoice(Invoice invoice) {
        log.info("payInvoice");
        invoice.setStatus("invoice_sent");
        Invoice saved_invoice = invoiceRepository.save(invoice);
        this.messagePublisher.publishInvoiceSentMessage(saved_invoice);
        return  saved_invoice;
    }

    public String deleteInvoice(long id) {
        invoiceRepository.deleteById(id);
        return "Invoice removed !! " + id;
    }

}
