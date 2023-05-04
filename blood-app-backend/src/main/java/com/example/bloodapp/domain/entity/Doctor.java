package com.example.bloodapp.domain.entity;

import com.example.bloodapp.controllers.dtos.DoctorResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor extends User {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String cnp;
    @Column
    private String county;

    public Doctor() {

    }

    public Doctor(String email, String password, String name, String surname, String cnp, String county) {
        setEmail(email);
        setPassword(password);
        setRole(Role.DOCTOR);
        this.name = name;
        this.surname = surname;
        this.cnp = cnp;
        this.county = county;

    }

    public Doctor(DoctorResponse doctorResponse) {
        setEmail(doctorResponse.getEmail());
        setPassword(doctorResponse.getPassword());
        setRole(Role.DOCTOR);
        this.name = doctorResponse.getName();
        this.surname = doctorResponse.getSurname();
        this.cnp = doctorResponse.getCnp();
        this.county = doctorResponse.getCounty();
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

    public void setCounty(String location) {
        this.county = location;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
}
