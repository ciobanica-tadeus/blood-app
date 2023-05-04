package com.example.bloodapp.controllers.dtos;

import com.example.bloodapp.domain.entity.Location;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class LocationResponse extends BaseResponse {
    private Long id;
    private String name;
    private String address;
    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeek startDay;
    private DayOfWeek endDay;
    private Integer availableNumberOfPeople;
    private String county;

    public LocationResponse() {

    }

    public LocationResponse(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.address = location.getAddress();
        this.startHour = location.getStartHour();
        this.endHour = location.getEndHour();
        this.startDay = location.getStartDay();
        this.endDay = location.getEndDay();
        this.availableNumberOfPeople = location.getAvailableNumberOfPeople();
        this.county = location.getCounty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
