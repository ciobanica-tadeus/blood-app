package com.example.bloodapp.controllers.dtos;

public class ConfirmedLocationsResponse extends BaseResponse {

    private Long donorId;
    private Long locationId;
    private Boolean isConfirmed;

    public ConfirmedLocationsResponse(Long donorID, Long locationID, Boolean isConfirmed) {
        this.donorId = donorID;
        this.locationId = locationID;
        this.isConfirmed = isConfirmed;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }
}
