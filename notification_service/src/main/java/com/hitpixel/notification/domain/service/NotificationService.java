package com.hitpixel.notification.domain.service;


import com.hitpixel.notification.Infrastructure.mq.CustomMessage;
import com.hitpixel.notification.domain.VO.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class NotificationService {
    HashMap<String, Email> emailHashMap = new HashMap<String, Email>();


    public NotificationService() {
        // Add keys and values (Country, City)
        this.emailHashMap.put("user_created", new Email("", "Welcome Email", "Thank you for joining us"));
        this.emailHashMap.put("invoice_sent", new Email("", "New invoice", "You have new invoice"));
        this.emailHashMap.put("client_charged", new Email("", "Account charged", "You have been charged"));
    }

    public void HandleMessage(CustomMessage message) {
        if (!this.emailHashMap.containsKey(message.getMessage())){
            return;
        }
        Email email = this.emailHashMap.get(message.getMessage());
        email.setToEmail(message.getEmail());
        this.sendEmail(email);
    }

    public void sendEmail(Email email) {
        log.info("sending email to:" + email.getToEmail());
        System.out.println(
                " send email to:" + email.getToEmail() +
                ", Title:" + email.getTitle() +
                ", Body:" + email.getBody());
    }

}
