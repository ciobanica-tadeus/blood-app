package com.example.bloodapp.controllers.dtos;

import com.example.bloodapp.domain.entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class ListDoctorResponse extends BaseResponse {

    private List<DoctorResponse> doctorList;

    public ListDoctorResponse(List<DoctorResponse> doctorList) {
        this.doctorList = doctorList;
    }

    public List<DoctorResponse> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DoctorResponse> doctorList) {
        this.doctorList = doctorList;
    }
}
