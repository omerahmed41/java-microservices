package com.hitpixel.payment.Infrastructure.repository;


import com.hitpixel.payment.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByUserId(Long userId);
}
