package com.hitpixel.payment.domain.command;


import com.hitpixel.payment.Infrastructure.mq.CustomMessage;

public interface ICommand {

    void execute();
    void setMessage(CustomMessage message);

    }
