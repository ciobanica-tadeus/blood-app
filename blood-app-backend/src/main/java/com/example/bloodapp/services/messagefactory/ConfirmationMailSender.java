package com.example.bloodapp.services.messagefactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//Concrete version of MessageSender which override send method
@Component
public class ConfirmationMailSender implements MessageSender {
    private final JavaMailSender javaMailSender;

    @Autowired
    public ConfirmationMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    @Async
    public void send(Message message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("blood.app2023@gmail.com");
            simpleMailMessage.setTo(message.getDestination());
            simpleMailMessage.setSubject(message.getSubject());
            simpleMailMessage.setText(message.getText());
            //send
            javaMailSender.send(simpleMailMessage);

        } catch (MailException mailException) {
            mailException.printStackTrace();
        }

    }
}
