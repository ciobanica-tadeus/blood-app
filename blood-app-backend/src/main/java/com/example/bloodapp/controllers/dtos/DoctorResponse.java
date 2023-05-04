package com.example.bloodapp.controllers.dtos;

import com.example.bloodapp.domain.entity.BaseEntity;
import com.example.bloodapp.domain.entity.Doctor;
import jakarta.persistence.Column;

public class DoctorResponse extends BaseResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String cnp;
    private String county;

    public DoctorResponse(){

    }

    public DoctorResponse(Doctor doctor){
        this.id = doctor.getId();
        this.email = doctor.getEmail();
        this.password = doctor.getPassword();
        this.name = doctor.getName();
        this.surname = doctor.getSurname();
        this.cnp = doctor.getCnp();
        this.county = doctor.getCounty();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
