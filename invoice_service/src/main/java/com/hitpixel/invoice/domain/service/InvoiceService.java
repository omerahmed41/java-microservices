package com.hitpixel.invoice.domain.service;


import com.hitpixel.invoice.Infrastructure.repository.InvoiceRepository;
import com.hitpixel.invoice.domain.VO.ResponseTemplateVO;
import com.hitpixel.invoice.domain.entity.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RestTemplate restTemplate;


    public Invoice createInvoice(Invoice invoice) {
        log.info("Inside saveUser of UserService");
        return invoiceRepository.save(invoice);
    }

    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceRepository.findAll());
    }

    public ResponseTemplateVO getInvoiceWithUserID(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Invoice invoice = invoiceRepository.findByUserId(userId);

//        Department department =
//                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
//                        ,Department.class);

        vo.setInvoice(invoice);
//        vo.setDepartment(department);

        return  vo;
    }

    public String deleteInvoice(long id) {
        invoiceRepository.deleteById(id);
        return "Invoice removed !! " + id;
    }

}
