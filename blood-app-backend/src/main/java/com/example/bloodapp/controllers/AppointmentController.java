package com.example.bloodapp.controllers;

import com.example.bloodapp.controllers.dtos.AppointmentResponse;
import com.example.bloodapp.controllers.dtos.BaseResponse;
import com.example.bloodapp.controllers.dtos.ConfirmedLocationsResponse;
import com.example.bloodapp.controllers.dtos.ErrorResponse;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.exceptions.NotFoundException;
import com.example.bloodapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse> createAppointment(@RequestBody AppointmentResponse appointmentResponse) {
        try {

            return new ResponseEntity<>(appointmentService.createNewAppointment(appointmentResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<BaseResponse> deleteAppointment(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(appointmentService.deleteAppointment(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get_all_appointments/{id}")
    public ResponseEntity<BaseResponse> getAppoinmentsByDonorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(appointmentService.getAppointmentsByDonorId(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get_all_appointments_by_location/{id}")
    public ResponseEntity<BaseResponse> getAppointmentsByLocationId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(appointmentService.getAppointmentsByLocationId(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/change_status/{id}")
    public ResponseEntity<BaseResponse> changeAppointmentStatus(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(appointmentService.changeAppointmentStatus(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get_all_appoinments_by_today/{id}")
    public ResponseEntity<BaseResponse> getTodaysAppointments(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(appointmentService.getAllApointmentsPageableByToday(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get_all_appoinments_pagination/{id}")
    public ResponseEntity<BaseResponse> getAllAppointmentsPageable(@PathVariable Long id,
                                                                   @RequestParam("pageNo") int pageNo,
                                                                   @RequestParam("pageSize") int pageSize) {
        try {
            return new ResponseEntity<>(appointmentService.getAllApointmentsPageable(id, pageNo, pageSize), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get_all_available_slots/{id}")
    public ResponseEntity<BaseResponse> getAllAvailableSlots(@PathVariable Long id){
        try {
            return new ResponseEntity<>(appointmentService.getAllAvailableSlots(id), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get_all_locations_confirmed")
    public ResponseEntity<BaseResponse> getAllConfirmedLocations(@RequestParam("donorId") Long donorId,
                                                                 @RequestParam("locationId") Long locationId){
        try{
            return new ResponseEntity<>(appointmentService.getAllConfirmedLocations(donorId,locationId), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


}
