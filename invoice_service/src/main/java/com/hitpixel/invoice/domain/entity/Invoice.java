package com.hitpixel.invoice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;
    private Long clientID;
    @Basic
//    ArrayList<Transaction> transactions;
    private Long amount;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String status;



//    @Transient
//    private String statusLabel;
//    public String getStatusLabel() {
//        return "getStatusLabel";
//    }

}
