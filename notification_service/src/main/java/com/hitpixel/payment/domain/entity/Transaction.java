package com.hitpixel.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientID;
    private Long orderID;
    private String email;
    private LocalDateTime createdAt;
    private Long amount;
    private String currency;
    private String card_type;
    private String status;
}
