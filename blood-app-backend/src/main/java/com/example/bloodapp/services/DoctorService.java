package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.DoctorResponse;
import com.example.bloodapp.controllers.dtos.ListDoctorResponse;
import com.example.bloodapp.domain.entity.Doctor;
import com.example.bloodapp.domain.repositories.DoctorRepository;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorResponse saveDoctor(DoctorResponse doctorResponse) {
        Optional<Doctor> doctors = doctorRepository.findByEmail(doctorResponse.getEmail());

        if (doctors.isPresent()) {
            throw new DuplicateEntityException("Duplicate doctor account with email: " + doctorResponse.getEmail());
        }
        Doctor doctor = new Doctor(doctorResponse);
        doctor = doctorRepository.save(doctor);
        return new DoctorResponse(doctor);

    }

    public DoctorResponse findDoctor(Long id) {
        Optional<Doctor> doctors = doctorRepository.findById(id);
        if (doctors.isEmpty()) {
            throw new NotFoundException("Doctor not found!");
        }
        Doctor doctor = doctors.get();
        System.out.println(doctor.getLocationDoctor().getName());
        return new DoctorResponse(doctor);
    }

    public DoctorResponse updateDoctor(Long id, DoctorResponse doctorResponse) {
        Optional<Doctor> doctors = doctorRepository.findById(id);
        if (doctors.isEmpty()) {
            throw new NotFoundException("Doctor not found!");
        }
        Doctor doctor = doctors.get();
        doctor.setEmail(doctorResponse.getEmail());
        doctor.setPassword(doctorResponse.getPassword());
        doctor.setName(doctorResponse.getName());
        doctor.setSurname(doctorResponse.getSurname());
        doctor.setCnp(doctorResponse.getCnp());
        doctor.setCounty(doctorResponse.getCounty());
        doctorRepository.save(doctor);
        return new DoctorResponse(doctor);
    }

    public Long deleteDoctor(Long id) {
        Optional<Doctor> doctors = doctorRepository.findById(id);
        if (doctors.isEmpty()) {
            throw new NotFoundException("Doctor not found!");
        }
        Doctor doctor = doctors.get();
        doctorRepository.delete(doctor);
        return doctor.getId();
    }

    public ListDoctorResponse getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            throw new NotFoundException("Doctors not found!");
        }
        List<DoctorResponse> doctorResponses = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorResponse doctorRes = new DoctorResponse(doctor);
            doctorResponses.add(doctorRes);
        }
        return new ListDoctorResponse(doctorResponses);
    }
}
