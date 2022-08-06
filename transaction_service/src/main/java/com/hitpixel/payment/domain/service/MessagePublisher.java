package com.hitpixel.payment.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitpixel.payment.Infrastructure.mq.CustomMessage;
import com.hitpixel.payment.Infrastructure.mq.MQConfig;
import com.hitpixel.payment.domain.entity.Transaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    public void publishMessage(String body, String email, String id, Map<String, Object> map) {
        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        message.setMessage(body);
        message.setEmail(email);
        message.setObject(map);
        message.setId(id);
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, message);

    }

    public void publishTransactionCreatedMessage(Transaction transaction) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(transaction, Map.class);
        this.publishMessage("transaction_created",
                transaction.getEmail(),
                transaction.getTransactionID().toString(), map);
    }

}
