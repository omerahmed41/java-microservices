package com.hitpixel.invoice.application.controller;

import com.hitpixel.invoice.domain.entity.Invoice;
import com.hitpixel.invoice.domain.service.InvoiceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InvoiceController.class})
@ExtendWith(SpringExtension.class)
class InvoiceControllerTest {
    @Autowired
    private InvoiceController invoiceController;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    @DisplayName("Should return the invoice with client id")
    void generateInvoiceBillWithClientIDShouldReturnTheInvoiceWithClientId() {
        Invoice invoice = new Invoice();
        invoice.setClientID(1L);
        invoice.setAmount(1L);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus("Pending");
        when(invoiceService.generateInvoiceBillWithClientID(1L)).thenReturn(invoice);

        Invoice result = invoiceController.generateInvoiceBillWithClientID(1L);

        assertEquals(invoice, result);
    }


    /**
     * Method under test: {@link InvoiceController#getInvoiceWithClientID(Long)}
     */
    @Test
    void testGetInvoiceWithClientID() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setAmount(10L);
        invoice.setClientID(1L);
        invoice.setCompletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setDueDate(LocalDateTime.of(1, 1, 1, 1, 1));
        invoice.setInvoiceId(123L);
        invoice.setStatus("Status");
        when(invoiceService.getInvoiceWithClientID((Long) any())).thenReturn(invoice);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices/client-id={id}", 123L);
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"invoiceId\":123,\"clientID\":1,\"amount\":10,\"createdAt\":[1,1,1,1,1],\"dueDate\":[1,1,1,1,1],\"completedAt"
                                        + "\":[1,1,1,1,1],\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link InvoiceController#getAllInvoices()}
     */
    @Test
    void testGetAllInvoices() throws Exception {
        when(invoiceService.getAllInvoices()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

