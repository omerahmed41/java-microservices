package com.hitpixel.payment.application;



import com.hitpixel.payment.Infrastructure.mq.CustomMessage;
import com.hitpixel.payment.Infrastructure.mq.MQConfig;
import com.hitpixel.payment.domain.command.ICommand;
import com.hitpixel.payment.domain.command.chargeClientCommand;
import com.hitpixel.payment.domain.service.ClientService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageListener {


    Map<String, ICommand> subscribers= new HashMap<>();
    private final ClientService clientService;



    @Autowired
    public MessageListener(ClientService clientService) {
        this.clientService = clientService;
        subscribers.put("invoice_sent", new chargeClientCommand(clientService));

    }


    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        this.HandleMessage(message);
    }


    public void HandleMessage(CustomMessage message) {
        System.out.println("new message:" + message);
        if (!this.subscribers.containsKey(message.getMessage())){
            return;
        }
        ICommand command = this.subscribers.get(message.getMessage());
        command.setMessage(message);
        try {
            command.execute();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
