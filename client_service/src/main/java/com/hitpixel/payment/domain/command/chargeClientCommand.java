package com.hitpixel.payment.domain.command;

import com.hitpixel.payment.Infrastructure.mq.CustomMessage;
import com.hitpixel.payment.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class chargeClientCommand implements ICommand {


    private CustomMessage message;

    private final ClientService clientService;



    @Autowired
    public chargeClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }


    public void setMessage(CustomMessage message) {
        this.message = message;
    }

    @Override
    public void execute() {
        CustomMessage message = this.message;
        long clientID = ((Number) message.getObject().get("clientID")).longValue();
        long amount = ((Number) message.getObject().get("amount")).longValue();
        this.clientService.chargeClient(clientID, amount);

    }

}
