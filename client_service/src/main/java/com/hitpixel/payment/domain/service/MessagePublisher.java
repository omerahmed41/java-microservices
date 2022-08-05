package com.hitpixel.payment.domain.service;

import com.hitpixel.payment.Infrastructure.mq.CustomMessage;
import com.hitpixel.payment.Infrastructure.mq.MQConfig;
import com.hitpixel.payment.domain.entity.Client;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    public void publishMessage(String body, String email, String id) {
        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        message.setMessage(body);
        message.setEmail(email);
        message.setId(id);
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, message);

    }

    public void publishUserCreatedMessage(Client client) {
        this.publishMessage("user_created", client.getEmail(), "");
    }

}
