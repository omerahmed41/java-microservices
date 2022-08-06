package com.hitpixel.invoice.application;


import com.hitpixel.invoice.Infrastructure.mq.CustomMessage;
import com.hitpixel.invoice.Infrastructure.mq.MQConfig;
import com.hitpixel.invoice.domain.service.ICommand;
import com.hitpixel.invoice.domain.service.InvoiceService;
import com.hitpixel.invoice.domain.service.createInvoiceCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageListener {


    Map<String, ICommand> subscribers= new HashMap<>();
    private final InvoiceService invoiceService;



    @Autowired
    public MessageListener(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
        subscribers.put("transaction_created", new createInvoiceCommand(invoiceService));

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
