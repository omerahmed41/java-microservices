package com.hitpixel.invoice.domain.service;

import com.hitpixel.invoice.Infrastructure.repository.InvoiceRepository;
import com.hitpixel.invoice.domain.entity.Invoice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Should delete the invoice when the id is valid")
    void deleteInvoiceWhenIdIsValid() {
        long id = 1L;
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(id);
        invoice.setClientID(1L);
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus("Pending");

        when(invoiceRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(invoice));

        assertEquals("Invoice removed !! " + id, invoiceService.deleteInvoice(id));
    }

    @Test
    @DisplayName("Should throws an exception when the id is invalid")
    void deleteInvoiceWhenIdIsInvalidThenThrowsException() {
        long id = 1L;
        doThrow(new IllegalArgumentException("Invalid id")).when(invoiceRepository).deleteById(id);

        Throwable exception =
                assertThrows(
                        IllegalArgumentException.class, () -> invoiceService.deleteInvoice(id));

        assertEquals("Invalid id", exception.getMessage());
    }

    @Test
    @DisplayName("Should update the status of the invoice to invoice_sent when the invoice exists")
    void payInvoiceWhenInvoiceExists() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1L);
        invoice.setClientID(1L);
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus("Pending");
        when(invoiceRepository.findByClientID(1L)).thenReturn(invoice);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        Invoice saved_invoice = invoiceService.payInvoiceWithClientID(1L);
        assertEquals("invoice_sent", saved_invoice.getStatus());
    }

    @Test
    @DisplayName("Should throw an exception when the invoice does not exist")
    void payInvoiceWhenInvoiceDoesNotExistThenThrowException() {
        Invoice invoice = new Invoice();
        invoice.setClientID(1L);
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus("Pending");
        when(invoiceRepository.findByClientID(1L)).thenReturn(null);
        assertThrows(
                NullPointerException.class,
                () -> {
                    invoiceService.payInvoiceWithClientID(1L);
                });
    }

    @Test
    @DisplayName("Should return the invoice when the clientID is found")
    void getInvoiceWithClientIDWhenClientIDIsFound() {
        Invoice invoice =
                new Invoice(
                        1L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Pending");
        when(invoiceRepository.findByClientID(1L)).thenReturn(invoice);
        assertEquals(invoice, invoiceService.getInvoiceWithClientID(1L));
    }

    @Test
    @DisplayName("Should return null when the clientID is not found")
    void getInvoiceWithClientIDWhenClientIDIsNotFound() {
        when(invoiceRepository.findByClientID(1L)).thenReturn(null);
        assertNull(invoiceService.getInvoiceWithClientID(1L));
    }

    @Test
    @DisplayName("Should return all invoices with due date less than or equal to now")
    void getAllDueDateInvoicesShouldReturnAllInvoicesWithDueDateLessThanOrEqualToNow() {
        Invoice invoice =
                new Invoice(
                        1L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Pending");
        when(invoiceRepository.findByDueDateLessThanEqual(LocalDateTime.now()))
                .thenReturn(List.of(invoice));

        List<Invoice> invoices = invoiceService.getAllDueDateInvoices();

        assertEquals(0, invoices.size());

    }

    @Test
    @DisplayName("Should return all invoices")
    void getAllInvoicesShouldReturnAllInvoices() {
        Invoice invoice =
                new Invoice(
                        1L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Pending");
        when(invoiceRepository.findAll()).thenReturn(List.of(invoice));
        assertEquals(1, invoiceService.getAllInvoices().getBody().size());
    }

    @Test
    @DisplayName("Should update the invoice when the invoice exists")
    void updateInvoiceWhenInvoiceExists() {
        Invoice invoice = new Invoice();
        invoice.setClientID(1L);
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus("Pending");

        when(invoiceRepository.findByClientID(1L)).thenReturn(invoice);

        invoiceService.updateInvoice(invoice);

        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    @DisplayName("Should save invoice")
    void createInvoiceShouldSaveInvoice() {
        Invoice invoice =
                new Invoice(
                        1L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Pending");
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        invoiceService.createInvoice(invoice);
        verify(invoiceRepository, times(1)).save(invoice);
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

