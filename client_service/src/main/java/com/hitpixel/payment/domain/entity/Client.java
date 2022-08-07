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
    private long userId;
    private String client;
    @Column(unique=true)
    private String email;
    private String billing_interval;
    private String fees_type;
    private Long fees;
    private Long credit = 100L;


}
