package com.hitpixel.notification.application;

import com.hitpixel.notification.Infrastructure.mq.CustomMessage;
import com.hitpixel.notification.Infrastructure.mq.MQConfig;
import com.hitpixel.notification.domain.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final NotificationService notificationService;



    @Autowired
    public MessageListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        this.notificationService.HandleMessage(message);
    }

}
