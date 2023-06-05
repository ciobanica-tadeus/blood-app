package com.example.bloodapp.controllers.dtos;

public class DeleteAppointmentResponse extends BaseResponse {
    private Long id;

    public DeleteAppointmentResponse() {

    }

    public DeleteAppointmentResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
