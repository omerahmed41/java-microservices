package com.hitpixel.payment.invoice;

import java.time.LocalDateTime;

public class Invoice {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private Integer amount;

    public Invoice() {
    }

    public Invoice(Long id, String name, String email, LocalDateTime createdAt, Integer amount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.amount = amount;
    }

    public Invoice(String name, String email, LocalDateTime createdAt, Integer amount) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", amount=" + amount +
                '}';
    }
}
