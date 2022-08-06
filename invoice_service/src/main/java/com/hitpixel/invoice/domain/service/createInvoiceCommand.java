package com.hitpixel.invoice.domain.service;

import com.hitpixel.invoice.Infrastructure.mq.CustomMessage;
import com.hitpixel.invoice.Infrastructure.repository.InvoiceRepository;
import com.hitpixel.invoice.domain.VO.Transaction;
import com.hitpixel.invoice.domain.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class createInvoiceCommand implements ICommand {


    private CustomMessage message;

    private final InvoiceService invoiceService;



    @Autowired
    public createInvoiceCommand(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    public void setMessage(CustomMessage message) {
        this.message = message;
    }

    @Override
    public void execute() {
        CustomMessage message = this.message;

        Invoice invoice = new Invoice();
        invoice.setUserId(message.getObject().get("clientID").toString());
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
//        invoice.setTransactions(new Transaction());
        this.invoiceService.createInvoice(invoice);
    }
}
