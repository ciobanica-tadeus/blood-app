package com.example.bloodapp.controllers.dtos;

import com.example.bloodapp.domain.entity.Appointment;
import com.example.bloodapp.domain.entity.MessageType;
import com.example.bloodapp.domain.entity.ReasonType;

import java.time.LocalDate;

public class AppointmentResponse extends BaseResponse {
    private Long id;
    private Long donorId;
    private String name;
    private Long locationId;
    private String locationName;
    private String locationAddress;
    private LocalDate appointmentDate;
    private Boolean confirmed;
    private MessageType messageType;

    public AppointmentResponse() {

    }

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.donorId = appointment.getDonor().getId();
        this.name = appointment.getDonor().getName() + " " + appointment.getDonor().getSurname();
        this.locationId = appointment.getLocation().getId();
        this.locationName = appointment.getLocation().getName();
        this.locationAddress = appointment.getLocation().getAddress();
        this.appointmentDate = appointment.getAppoinmentDate();
        this.confirmed = appointment.isConfirmed();
        this.messageType = appointment.getMessageType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
