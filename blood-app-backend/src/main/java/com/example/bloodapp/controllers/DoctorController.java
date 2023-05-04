package com.example.bloodapp.controllers;

import com.example.bloodapp.controllers.dtos.BaseResponse;
import com.example.bloodapp.controllers.dtos.DoctorResponse;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/create_doctor")
    public ResponseEntity<BaseResponse> createDoctor(@RequestBody DoctorResponse doctorResponse) {
        try {
            return new ResponseEntity<>(doctorService.saveDoctor(doctorResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<BaseResponse> getDoctor(@RequestParam Long id){
        try {
            return new ResponseEntity<>(doctorService.findDoctor(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<BaseResponse> updateDoctor(@PathVariable Long id,
                                                     @RequestBody DoctorResponse doctorResponse){
        try {
            return new ResponseEntity<>(doctorService.updateDoctor(id, doctorResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteDoctor(@PathVariable Long id){
        try {
            return new ResponseEntity<>(doctorService.deleteDoctor(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
