package com.example.bloodapp.controllers.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListAppointmentResponse extends BaseResponse{

    public List<AppointmentResponse> appointmentResponses;
    private Integer numberOfAppointments;

    public ListAppointmentResponse(List<AppointmentResponse> appointmentResponses){
        this.appointmentResponses = appointmentResponses;
        this.numberOfAppointments = appointmentResponses.size();
    }

    public List<AppointmentResponse> getAppointmentResponses() {
        return appointmentResponses;
    }

    public void setAppointmentResponses(List<AppointmentResponse> appointmentResponses) {
        this.appointmentResponses = appointmentResponses;
    }

    public Integer getNumberOfAppointments() {
        return numberOfAppointments;
    }

    public void setNumberOfAppointments(Integer numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }
}
