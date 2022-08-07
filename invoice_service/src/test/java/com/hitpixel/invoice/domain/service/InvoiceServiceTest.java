package com.hitpixel.invoice.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hitpixel.invoice.Infrastructure.repository.InvoiceRepository;
import com.hitpixel.invoice.domain.entity.Invoice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {InvoiceService.class})
@ExtendWith(SpringExtension.class)
class InvoiceServiceTest {
    @MockBean
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private MessagePublisher messagePublisher;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link InvoiceService#createInvoice(Invoice)}
     */
    @Test
    void testCreateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setAmount(10L);
        invoice.setClientID(1L);
        invoice.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setInvoiceId(123L);
        invoice.setStatus("Status");
        when(invoiceRepository.save((Invoice) any())).thenReturn(invoice);

        Invoice invoice1 = new Invoice();
        invoice1.setAmount(10L);
        invoice1.setClientID(1L);
        invoice1.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice1.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice1.setInvoiceId(123L);
        invoice1.setStatus("Status");
        invoiceService.createInvoice(invoice1);
        verify(invoiceRepository).save((Invoice) any());
        assertEquals(1L, invoice1.getAmount().longValue());
        assertEquals("Pending", invoice1.getStatus());
    }

    /**
     * Method under test: {@link InvoiceService#updateInvoice(Invoice)}
     */
    @Test
    void testUpdateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setAmount(10L);
        invoice.setClientID(1L);
        invoice.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setInvoiceId(123L);
        invoice.setStatus("Status");
        when(invoiceRepository.save((Invoice) any())).thenReturn(invoice);


    }

    /**
     * Method under test: {@link InvoiceService#getAllInvoices()}
     */
    @Test
    void testGetAllInvoices() {
        when(invoiceRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Invoice>> actualAllInvoices = invoiceService.getAllInvoices();
        assertTrue(actualAllInvoices.hasBody());
        assertEquals(HttpStatus.OK, actualAllInvoices.getStatusCode());
        assertTrue(actualAllInvoices.getHeaders().isEmpty());
        verify(invoiceRepository).findAll();
    }

    /**
     * Method under test: {@link InvoiceService#getAllDueDateInvoices()}
     */
    @Test
    void testGetAllDueDateInvoices() {
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        when(invoiceRepository.findByDueDateLessThanEqual((LocalDateTime) any())).thenReturn(invoiceList);
        List<Invoice> actualAllDueDateInvoices = invoiceService.getAllDueDateInvoices();
        assertSame(invoiceList, actualAllDueDateInvoices);
        assertTrue(actualAllDueDateInvoices.isEmpty());
        verify(invoiceRepository).findByDueDateLessThanEqual((LocalDateTime) any());
    }

    /**
     * Method under test: {@link InvoiceService#getInvoiceWithClientID(Long)}
     */
    @Test
    void testGetInvoiceWithClientID() {
        Invoice invoice = new Invoice();
        invoice.setAmount(10L);
        invoice.setClientID(1L);
        invoice.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setInvoiceId(123L);
        invoice.setStatus("Status");
        when(invoiceRepository.findByClientID((Long) any())).thenReturn(invoice);
        assertSame(invoice, invoiceService.getInvoiceWithClientID(1L));
        verify(invoiceRepository).findByClientID((Long) any());
    }

    /**
     * Method under test: {@link InvoiceService#payInvoiceWithClientID(Long)}
     */
    @Test
    void testPayInvoiceWithClientID() {


        Invoice invoice1 = new Invoice();
        invoice1.setAmount(10L);
        invoice1.setClientID(1L);
        invoice1.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice1.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice1.setInvoiceId(123L);
        invoice1.setStatus("Status");
        when(invoiceRepository.save((Invoice) any())).thenReturn(invoice1);
        doNothing().when(messagePublisher).publishInvoiceSentMessage((Invoice) any());
        assertSame(invoice1, invoiceService.payInvoiceWithClientID(1L));
        verify(invoiceRepository).findByClientID((Long) any());
        verify(invoiceRepository).save((Invoice) any());
        verify(messagePublisher).publishInvoiceSentMessage((Invoice) any());
    }

    /**
     * Method under test: {@link InvoiceService#payInvoice(Invoice)}
     */
    @Test
    void testPayInvoice() {
        Invoice invoice = new Invoice();
        invoice.setAmount(10L);
        invoice.setClientID(1L);
        invoice.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setInvoiceId(123L);
        invoice.setStatus("Status");
        when(invoiceRepository.save((Invoice) any())).thenReturn(invoice);
        doNothing().when(messagePublisher).publishInvoiceSentMessage((Invoice) any());

    }

    /**
     * Method under test: {@link InvoiceService#deleteInvoice(long)}
     */
    @Test
    void testDeleteInvoice() {
        doNothing().when(invoiceRepository).deleteById((Long) any());
        assertEquals("Invoice removed !! 123", invoiceService.deleteInvoice(123L));
        verify(invoiceRepository).deleteById((Long) any());
    }
}

