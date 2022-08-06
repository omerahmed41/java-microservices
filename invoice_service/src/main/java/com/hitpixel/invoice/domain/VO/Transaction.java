package com.hitpixel.invoice.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long transactionID;
    private Long clientID;
    private Long orderID;
    private String email;
    private LocalDateTime createdAt;
    private Long amount;
    private String currency;
    private String card_type;
    private String status;
}
