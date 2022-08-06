package com.hitpixel.invoice.domain.service;

import com.hitpixel.invoice.Infrastructure.mq.CustomMessage;

public interface ICommand {

    void execute();
    void setMessage(CustomMessage message);

    }
