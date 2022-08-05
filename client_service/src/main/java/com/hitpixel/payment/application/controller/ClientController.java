package com.hitpixel.payment.application.controller;


import com.hitpixel.payment.domain.VO.ResponseTemplateVO;
import com.hitpixel.payment.domain.entity.Client;
import com.hitpixel.payment.domain.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {


    private final ClientService clientService;



    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/")
    public Client saveUser(@RequestBody Client client) {
        log.info("Inside saveUser of UserController");
        return clientService.saveUser(client);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return clientService.getUserWithDepartment(userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllProduct() {
        return clientService.getAllClient();
    }
    @PutMapping("/{id}")
    public Client updateProduct(@PathVariable int id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return clientService.deleteProduct(id);
    }



}
