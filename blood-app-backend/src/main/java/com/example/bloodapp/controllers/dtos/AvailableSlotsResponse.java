package com.example.bloodapp.controllers.dtos;

import java.time.LocalDate;
import java.util.List;

public class AvailableSlotsResponse extends BaseResponse {
    LocalDate startDate;
    LocalDate endDate;
    List<LocalDate> availableDays;

    public AvailableSlotsResponse(LocalDate startDate, LocalDate endDate, List<LocalDate> availableDays) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableDays = availableDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<LocalDate> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<LocalDate> availableDays) {
        this.availableDays = availableDays;
    }
}
