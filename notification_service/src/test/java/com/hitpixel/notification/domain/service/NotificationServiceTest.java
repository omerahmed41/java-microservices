package com.hitpixel.notification.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hitpixel.notification.Infrastructure.mq.CustomMessage;
import com.hitpixel.notification.domain.VO.Email;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;

    /**
     * Method under test: {@link NotificationService#HandleMessage(CustomMessage)}
     */
    @Test
    void testHandleMessage() {
        notificationService.HandleMessage(new CustomMessage());
        assertEquals(3, notificationService.emailHashMap.size());
    }

    /**
     * Method under test: {@link NotificationService#HandleMessage(CustomMessage)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleMessage2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.hitpixel.notification.Infrastructure.mq.CustomMessage.getMessage()" because "message" is null
        //       at com.hitpixel.notification.domain.service.NotificationService.HandleMessage(NotificationService.java:25)
        //   In order to prevent HandleMessage(CustomMessage)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   HandleMessage(CustomMessage).
        //   See https://diff.blue/R013 to resolve this issue.

        notificationService.HandleMessage(null);
    }

    /**
     * Method under test: {@link NotificationService#HandleMessage(CustomMessage)}
     */
    @Test
    void testHandleMessage3() {
        CustomMessage customMessage = mock(CustomMessage.class);
        when(customMessage.getMessage()).thenReturn("Not all who wander are lost");
        notificationService.HandleMessage(customMessage);
        verify(customMessage).getMessage();
    }

    /**
     * Method under test: {@link NotificationService#sendEmail(Email)}
     */
    @Test
    void testSendEmail() {
        Email email = new Email("jane.doe@example.org", "Dr", "Not all who wander are lost");

        notificationService.sendEmail(email);
        assertEquals("Not all who wander are lost", email.getBody());
        assertEquals("jane.doe@example.org", email.getToEmail());
        assertEquals("Dr", email.getTitle());
        assertEquals(3, notificationService.emailHashMap.size());
    }

    /**
     * Method under test: {@link NotificationService#sendEmail(Email)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmail2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.hitpixel.notification.domain.VO.Email.getToEmail()" because "email" is null
        //       at com.hitpixel.notification.domain.service.NotificationService.sendEmail(NotificationService.java:34)
        //   In order to prevent sendEmail(Email)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   sendEmail(Email).
        //   See https://diff.blue/R013 to resolve this issue.

        notificationService.sendEmail(null);
    }

    /**
     * Method under test: {@link NotificationService#sendEmail(Email)}
     */
    @Test
    void testSendEmail3() {
        Email email = mock(Email.class);
        when(email.getBody()).thenReturn("Not all who wander are lost");
        when(email.getTitle()).thenReturn("Dr");
        when(email.getToEmail()).thenReturn("jane.doe@example.org");
        notificationService.sendEmail(email);
        verify(email).getBody();
        verify(email).getTitle();
        verify(email, atLeast(1)).getToEmail();
    }
}

