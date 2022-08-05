package com.hitpixel.payment.domain.service;


import com.hitpixel.payment.Infrastructure.repository.ClientRepository;
import com.hitpixel.payment.domain.VO.ResponseTemplateVO;
import com.hitpixel.payment.domain.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Client saveUser(Client client) {
        log.info("Inside saveUser of UserService");
        return clientRepository.save(client);
    }

    public ResponseEntity<List<Client>> getAllClient() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Client client = clientRepository.findByUserId(userId);

//        Department department =
//                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
//                        ,Department.class);

        vo.setClient(client);
//        vo.setDepartment(department);

        return  vo;
    }

    public String deleteProduct(long id) {
        clientRepository.deleteById(id);
        return "Client removed !! " + id;
    }

    public Client updateClient(long id,Client client) {
        Client existingClient = clientRepository.findById(id).orElseThrow();
        existingClient.setEmail(client.getEmail());
        existingClient.setFees(client.getFees());
        return clientRepository.save(existingClient);
    }
}
