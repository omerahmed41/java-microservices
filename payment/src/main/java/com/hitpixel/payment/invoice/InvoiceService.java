package com.hitpixel.payment.invoice;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    public List<Invoice> getStudents () {
        return List.of(
                new Invoice(1L,
                        "omer",
                        "omer@gmail.com",
                        LocalDateTime.now(),
                        20
                )
        );
    }
}
