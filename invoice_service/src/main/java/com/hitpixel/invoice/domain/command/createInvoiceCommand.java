package com.hitpixel.invoice.domain.command;

import com.hitpixel.invoice.Infrastructure.mq.CustomMessage;
import com.hitpixel.invoice.domain.entity.Invoice;
import com.hitpixel.invoice.domain.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

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
        long clientID = ((Number) message.getObject().get("clientID")).longValue();
        Invoice invoice = this.invoiceService.getInvoiceWithClientID(clientID);
        if (invoice == null || !Objects.equals(invoice.getStatus(), "Pending")){
            invoice = new Invoice();
            invoice.setClientID(clientID);
            this.invoiceService.createInvoice(invoice);
        } else{
            this.invoiceService.updateInvoice(invoice);
        }

    }

}
