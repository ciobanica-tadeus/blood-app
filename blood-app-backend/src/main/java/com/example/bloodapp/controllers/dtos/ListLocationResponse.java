package com.example.bloodapp.controllers.dtos;

import java.util.List;

public class ListLocationResponse extends BaseResponse{
    private List<LocationResponse> locationResponses;

    public ListLocationResponse(List<LocationResponse> locationResponses) {
        this.locationResponses = locationResponses;
    }

    public List<LocationResponse> getLocationResponses() {
        return locationResponses;
    }

    public void setLocationResponses(List<LocationResponse> locationResponses) {
        this.locationResponses = locationResponses;
    }
}
