package com.example.bloodapp.controllers.dtos;

import java.util.List;

public class RatingResponse extends BaseResponse{
    private Long locationId;
    private Integer ratingValue;
    private Float averageValue;

    public RatingResponse(Long locationId, Integer ratingValue, Float averageValue) {
        this.locationId = locationId;
        this.ratingValue = ratingValue;
        this.averageValue = averageValue;
    }

    public RatingResponse(Float averageValue) {
        this.averageValue = averageValue;
    }

    public Float getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Float averageValue) {
        this.averageValue = averageValue;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }
}
