package com.example.bloodapp.domain.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private LocalTime startHour;
    @Column
    private LocalTime endHour;
    @Column
    private DayOfWeek startDay;
    @Column
    private DayOfWeek endDay;
    @Column
    private Integer availableNumberOfPeople;
    @Column
    private String county;
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public Location() {

    }

    public Location(String name, String address, LocalTime startHour, LocalTime endHour, DayOfWeek startDay, DayOfWeek endDay, Integer availableNumberOfPeople, String county) {
        this.name = name;
        this.address = address;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startDay = startDay;
        this.endDay = endDay;
        this.availableNumberOfPeople = availableNumberOfPeople;
        this.county = county;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public DayOfWeek getStartDay() {
        return startDay;
    }

    public void setStartDay(DayOfWeek startDay) {
        this.startDay = startDay;
    }

    public DayOfWeek getEndDay() {
        return endDay;
    }

    public void setEndDay(DayOfWeek endDay) {
        this.endDay = endDay;
    }

    public Integer getAvailableNumberOfPeople() {
        return availableNumberOfPeople;
    }

    public void setAvailableNumberOfPeople(Integer availableNumberOfPeople) {
        this.availableNumberOfPeople = availableNumberOfPeople;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
