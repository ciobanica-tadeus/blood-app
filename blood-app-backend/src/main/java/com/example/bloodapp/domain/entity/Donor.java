package com.example.bloodapp.domain.entity;

import com.example.bloodapp.controllers.dtos.DonorResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "donors")
public class Donor extends User {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String bloodGroup;
    @Column
    private String county;

    public Donor() {
    }

    public Donor(DonorResponse donorResponse) {
        setEmail(donorResponse.getEmail());
        setPassword(donorResponse.getPassword());
        setRole(Role.DONOR);
        this.name = donorResponse.getName();
        this.surname = donorResponse.getSurname();
        this.county = donorResponse.getCounty();
    }

    public Donor(String email, String password, String name, String surname, String county) {
        setEmail(email);
        setPassword(password);
        setRole(Role.DONOR);
        this.name = name;
        this.surname = surname;
        this.county = county;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
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
}
