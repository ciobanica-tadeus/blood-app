package com.example.bloodapp.controllers;

import com.example.bloodapp.controllers.dtos.DeleteDonorResponse;
import com.example.bloodapp.controllers.dtos.ErrorResponse;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.controllers.dtos.BaseResponse;
import com.example.bloodapp.controllers.dtos.DonorResponse;
import com.example.bloodapp.exceptions.NotFoundException;
import com.example.bloodapp.services.DonorService;
import com.example.bloodapp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/donor")
public class DonorController {
    private final DonorService donorService;
    private final LocationService locationService;

    @Autowired
    public DonorController(DonorService donorService, LocationService locationService) {
        this.donorService = donorService;
        this.locationService = locationService;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<BaseResponse> findDonor(@PathVariable Long id){
        try{
            return new ResponseEntity<>(donorService.findByID(id), HttpStatus.OK);
        }catch (DuplicateEntityException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse> saveDonor(@RequestBody DonorResponse donorResponse){
        try{
            return new ResponseEntity<>(donorService.saveDonor(donorResponse), HttpStatus.OK);
        }catch (DuplicateEntityException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<BaseResponse> updateDonor(@PathVariable Long id,
                                                    @RequestBody DonorResponse donorResponse){
        try{
            return new ResponseEntity<>(donorService.editDonor(id, donorResponse),HttpStatus.OK);
        }catch (DuplicateEntityException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DeleteDonorResponse> deleteDonor(@PathVariable Long id){
        try{
            return new ResponseEntity<>(donorService.deleteDonor(id),HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "get_all_locations/{id}")
    public ResponseEntity<BaseResponse> getAll(@PathVariable Long id){
        try{
            return new ResponseEntity<>(locationService.getAll(id), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
