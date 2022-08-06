package com.hitpixel.invoice.domain.entity;

import com.hitpixel.invoice.domain.VO.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;
    private String userId;
    @Basic
//    ArrayList<Transaction> transactions;
    private Long amount;
    private LocalDateTime createdAt;



//    @Transient
//    private String statusLabel;
//    public String getStatusLabel() {
//        return "getStatusLabel";
//    }

}
