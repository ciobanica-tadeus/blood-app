package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.*;
import com.example.bloodapp.domain.entity.*;
import com.example.bloodapp.domain.repositories.AppointmentRepository;
import com.example.bloodapp.domain.repositories.DonorRepository;
import com.example.bloodapp.domain.repositories.LocationRepository;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.exceptions.NotFoundException;
import com.example.bloodapp.services.messagefactory.Message;
import com.example.bloodapp.services.messagefactory.MessageSender;
import com.example.bloodapp.services.messagefactory.MessageSenderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DonorRepository donorRepository;
    private final LocationRepository locationRepository;
    private final MessageSenderFactory messageSenderFactory;
    private final Message confirmMessage;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, DonorRepository donorRepository, LocationRepository locationRepository, MessageSenderFactory messageSenderFactory) {
        this.appointmentRepository = appointmentRepository;
        this.donorRepository = donorRepository;
        this.locationRepository = locationRepository;
        this.messageSenderFactory = messageSenderFactory;
        this.confirmMessage = new Message(ReasonType.CONFIRMATION);
    }

    public AppointmentResponse createNewAppointment(AppointmentResponse appointmentResponse) {
        if(  appointmentResponse.getAppointmentDate().isBefore(LocalDate.now())){
            throw new NotFoundException("Can't make an appointment with requested Date");
        }
        Optional<Location> locations = locationRepository.findById(appointmentResponse.getLocationId());
        if (locations.isEmpty()) {
            throw new NotFoundException("Location with inserted id not found!");
        }
        Optional<Donor> donors = donorRepository.findById(appointmentResponse.getDonorId());
        if (donors.isEmpty()) {
            throw new NotFoundException("Donor with inserted id not found!");
        }
        Location location = locations.get();
        Donor donor = donors.get();
        if (!donor.getCounty().equals(location.getCounty())) {
            throw new NotFoundException("Can't create appointement to another county location, use the correct location instead!");
        }
        if(location.getAvailableNumberOfPeople() <= appointmentRepository.countAppointmentsByAppoinmentDate(appointmentResponse.getAppointmentDate())){
            throw new DuplicateEntityException("No available spots for this date!");
        }
        Appointment appointment = new Appointment();
        appointment.setConfirmed(false);
        appointment.setDonor(donor);
        appointment.setAppoinmentDate(appointmentResponse.getAppointmentDate());
        appointment.setLocation(location);
        appointment.setMessageType(MessageType.EMAIL);
        appointmentRepository.save(appointment);
        //Create CONFIRMATION mail
        Message message = confirmMessage.clone();
        message.addMessageInformation(appointment);
        //send email
/*        MessageSender messageSender = messageSenderFactory.create(MessageType.EMAIL);
        messageSender.send(message);*/
        return new AppointmentResponse(appointment);

    }

    public ListAppointmentResponse getAppointmentsByDonorId(Long id) {
        List<Appointment> appointments = appointmentRepository.findByDonor_Id(id);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentResponses.add(new AppointmentResponse(appointment));
        }
        return new ListAppointmentResponse(appointmentResponses);
    }

    public ListAppointmentResponse getAppointmentsByLocationId(Long id) {
        List<Appointment> appointments = appointmentRepository.findByLocation_Id(id);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentResponses.add(new AppointmentResponse(appointment));
        }
        return new ListAppointmentResponse(appointmentResponses);
    }

    public AppointmentResponse changeAppointmentStatus(Long id) {
        Optional<Appointment> appointments = appointmentRepository.findById(id);
        if(appointments.isEmpty()){
            throw new NotFoundException("Appointment not found!");
        }
        Appointment appointment = appointments.get();
        appointment.setConfirmed(true);
        appointmentRepository.save(appointment);
        return new AppointmentResponse(appointment);
    }

    public ListAppointmentResponse getAllApointmentsPageableByToday(Long id) {
        List<Appointment> appointments = appointmentRepository.findAllByLocation_IdAndAndAppoinmentDate(id, LocalDate.now());
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentResponses.add(new AppointmentResponse(appointment));
        }
        return new ListAppointmentResponse(appointmentResponses);
    }

    public ListAppointmentResponse getAllApointmentsPageable(Long id, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by("appoinmentDate"));
        List<Appointment> appointments = appointmentRepository.findByLocation_Id(id, pageable);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentResponses.add(new AppointmentResponse(appointment));
        }
        return new ListAppointmentResponse(appointmentResponses);


    }

    public DeleteAppointmentResponse deleteAppointment(Long id) {
        Optional<Appointment> appointments = appointmentRepository.findById(id);
        if(appointments.isEmpty()){
            throw new NotFoundException("The appointment with inserted id can't be found!");
        }
        Appointment appointment = appointments.get();
        if(appointment.getAppoinmentDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("The appointment from the past can't be deleted!");
        }
        appointmentRepository.delete(appointment);
        return new DeleteAppointmentResponse(appointment.getId());
    }

    public AvailableSlotsResponse getAllAvailableSlots(Long id) {
        Optional<Location> locations = locationRepository.findById(id);
        if(locations.isEmpty()){
            throw new NotFoundException("The location with provided id is not found!");
        }
        List<LocalDate> availability = new ArrayList<>();
        Location location = locations.get();
        int days = 60;
        LocalDate startDate = LocalDate.now();
        LocalDate day = null;
        for(int i = 0 ; i < days; i++){
            day = startDate.plusDays(i);
            int nmbAppoinments = appointmentRepository.countAppointmentsByAppoinmentDateAndLocation_Id(day,id);
            if(nmbAppoinments >= location.getAvailableNumberOfPeople()) {
                availability.add(day);
            }
        }
        return new AvailableSlotsResponse(startDate,day,availability);
    }

    public ConfirmedLocationsResponse getAllConfirmedLocations(Long donorId, Long locationId) {
        System.out.println("Donor ID : " + donorId);
        System.out.println("Location ID : " + locationId);
        Integer confirmedNumber = appointmentRepository
                .countAppointmentsByDonor_IdAndLocation_IdAndConfirmed(donorId,locationId, true);
        if(confirmedNumber > 0){
            return new ConfirmedLocationsResponse(donorId,locationId,true);
        }else{
            return new ConfirmedLocationsResponse(donorId,locationId,false);
        }

    }
}
