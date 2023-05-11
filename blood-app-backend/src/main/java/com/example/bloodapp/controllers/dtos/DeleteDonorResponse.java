package com.example.bloodapp.controllers.dtos;

public class DeleteDonorResponse extends BaseResponse {

    private Long id;


    public DeleteDonorResponse() {

    }

    public DeleteDonorResponse(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
