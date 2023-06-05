package com.example.bloodapp.services.messagefactory;

import com.example.bloodapp.domain.entity.Appointment;
import com.example.bloodapp.domain.entity.MessageType;
import com.example.bloodapp.domain.entity.ReasonType;
import com.example.bloodapp.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReminderMailSender implements MessageSender{
    private final AppointmentRepository appointmentRepository;
    private final MessageSenderFactory messageSenderFactory;
    private final JavaMailSender javaMailSender;

    @Autowired
    public ReminderMailSender(AppointmentRepository appointmentRepository, MessageSenderFactory messageSenderFactory, JavaMailSender javaMailSender) {
        this.appointmentRepository = appointmentRepository;
        this.messageSenderFactory = messageSenderFactory;
        this.javaMailSender = javaMailSender;
    }

    @Scheduled(cron = "0 12 00 * * *", zone = "Europe/Bucharest")
    @Async
    public void prepareScheduledMessages() {
        List<Appointment> appointments = appointmentRepository.findAllByAppoinmentDate(LocalDate.now());
        for (Appointment appointment : appointments) {
            Message message = new Message(ReasonType.REMINDER);
            message.addMessageInformation(appointment);
            MessageSender messageSender = messageSenderFactory.create(MessageType.EMAIL);
            messageSender.send(message);
        }
    }

    @Override
    public void send(Message message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("blood.app2023@gmail.com");
            simpleMailMessage.setTo(message.getDestination());
            simpleMailMessage.setSubject(message.getSubject());
            simpleMailMessage.setText(message.getText());
            //send
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
