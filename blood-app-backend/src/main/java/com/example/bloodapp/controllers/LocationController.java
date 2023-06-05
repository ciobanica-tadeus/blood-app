package com.example.bloodapp.controllers;

import com.example.bloodapp.controllers.dtos.BaseResponse;
import com.example.bloodapp.controllers.dtos.ErrorResponse;
import com.example.bloodapp.controllers.dtos.RatingResponse;
import com.example.bloodapp.exceptions.DuplicateEntityException;
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

    @PostMapping(value = "/add_new_rating")
    public ResponseEntity<BaseResponse> setNewRating(@RequestBody RatingResponse ratingResponse){
        try{
            return new ResponseEntity<>(locationService.setNewRatingValue(ratingResponse),HttpStatus.OK);
        }catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/getRating/{id}")
    public ResponseEntity<BaseResponse> getRating(@PathVariable Long id){
        try{
            return new ResponseEntity<>(locationService.getRating(id),HttpStatus.OK);
        }catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
