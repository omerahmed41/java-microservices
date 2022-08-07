package com.hitpixel.invoice.Infrastructure.job;

import com.hitpixel.invoice.domain.entity.Invoice;
import com.hitpixel.invoice.domain.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CheckInvoiceDueDate
{

    private final InvoiceService invoiceService;



    @Autowired
    public CheckInvoiceDueDate(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


        @Scheduled(fixedRate = 10000)
//    @Scheduled(cron = "@daily")
    @Async
    public void CheckInvoiceDueDateTask() {
        log.info("Run CheckInvoiceDueDateTask at" + LocalDateTime.now());
        List<Invoice> Invoices =  invoiceService.getAllDueDateInvoices();
        Invoices.forEach(invoiceService::sentInvoiceBill);
    }

}
