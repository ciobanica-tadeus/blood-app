package com.example.bloodapp.controllers;

import com.example.bloodapp.controllers.dtos.BaseResponse;
import com.example.bloodapp.controllers.dtos.ErrorResponse;
import com.example.bloodapp.exceptions.NotFoundException;
import com.example.bloodapp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


}
