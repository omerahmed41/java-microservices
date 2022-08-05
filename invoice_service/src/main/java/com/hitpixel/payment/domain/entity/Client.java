package com.hitpixel.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String email;
    private String billing_interval;
    private String fees_type;
    private Long fees;
    private String client;


    @Transient
    private String statusLabel;
    public String getStatusLabel() {
        return "getStatusLabel";
    }

}
