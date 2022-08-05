package com.hitpixel.payment.domain.service;



import com.hitpixel.payment.Infrastructure.repository.TransactionRepository;
import com.hitpixel.payment.domain.VO.ResponseTemplateVO;
import com.hitpixel.payment.domain.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private final MessagePublisher messagePublisher;



    @Autowired
    public TransactionService(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction saveTransaction(Transaction transaction) {
        log.info("Inside saveUser of UserService");
        Transaction saved_transaction =  transactionRepository.save(transaction);
        this.messagePublisher.publishTransactionCreatedMessage(saved_transaction);
        return saved_transaction;
    }

    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    public ResponseTemplateVO getTransaction(Long transactionID) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Transaction transaction = transactionRepository.findById(transactionID).orElseThrow();

//        Department department =
//                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
//                        ,Department.class);


        vo.setTransaction(transaction);
//        vo.setDepartment(department);

        return  vo;
    }

    public String deleteTransaction(long id) {
        transactionRepository.deleteById(id);
        return "Transaction removed !! " + id;
    }

    public Transaction updateTransaction(long id,Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id).orElseThrow();
        existingTransaction.setEmail(transaction.getEmail());
//        existingTransaction.setFees(transaction.getFees());
        return transactionRepository.save(existingTransaction);
    }
}
