package com.hitpixel.invoice.Infrastructure.repository;


import com.hitpixel.invoice.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    Invoice findByClientID(Long clientID);
}
