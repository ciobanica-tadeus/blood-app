package com.example.bloodapp.services.messagefactory;

import com.example.bloodapp.domain.entity.Appointment;
import com.example.bloodapp.domain.entity.MessageType;
import com.example.bloodapp.domain.entity.ReasonType;
import com.example.bloodapp.domain.repositories.AppointmentRepository;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SmSSender implements MessageSender {
    private final AppointmentRepository appointmentRepository;
    private final MessageSenderFactory messageSenderFactory;

    public SmSSender(AppointmentRepository appointmentRepository, MessageSenderFactory messageSenderFactory) {
        this.appointmentRepository = appointmentRepository;
        this.messageSenderFactory = messageSenderFactory;
    }


    @Scheduled(cron = "0 12 11 * * *", zone = "Europe/Bucharest")
    @Async
    public void prepareScheduledSMS() {
        List<Appointment> appointments = appointmentRepository.findAllByAppoinmentDate(LocalDate.now());
        for (Appointment appointment : appointments) {
            if (appointment.getMessageType().equals(MessageType.SMS)) {
                Message message = new Message(ReasonType.REMINDER);
                message.addMessageInformation(appointment);
                MessageSender messageSender = messageSenderFactory.create(MessageType.SMS);
                messageSender.send(message);
            }
        }
    }

    @Override
    public void send(Message message) {
        try {
            Twilio.init("AccountSID", "AuthToken");
            com.twilio.rest.api.v2010.account.Message smsMessage = com.twilio.rest.api.v2010.account.Message.creator(
                            //to
                            new PhoneNumber(message.getDestination()),
                            //from
                            new PhoneNumber("+APIphoneNumber"),
                            message.getText())
                    .create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
