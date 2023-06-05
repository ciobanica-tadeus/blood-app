package com.example.bloodapp.services.messagefactory;

import com.example.bloodapp.domain.entity.MessageType;
import com.example.bloodapp.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//FACTORY Method Design Pattern
//CREATOR with create method
@Service
public class MessageSenderFactory {
    private final JavaMailSender javaMailSender;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public MessageSenderFactory(JavaMailSender javaMailSender, AppointmentRepository appointmentRepository){
        this.javaMailSender = javaMailSender;
        this.appointmentRepository = appointmentRepository;
    }

    public MessageSender create(MessageType messageType){
        if(messageType.equals(MessageType.EMAIL)){
            return new ConfirmationMailSender(javaMailSender);
        }
        if(messageType.equals(MessageType.SMS)){
            return new SmSSender(appointmentRepository,this);
        }
        return null;
    }




}
