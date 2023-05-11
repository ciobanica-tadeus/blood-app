package com.example.bloodapp.config;

import com.example.bloodapp.domain.entity.*;
import com.example.bloodapp.domain.repositories.LocationRepository;
import com.example.bloodapp.domain.repositories.UserRepository;
import com.example.bloodapp.domain.repositories.DoctorRepository;
import com.example.bloodapp.domain.repositories.DonorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class DataBaseInitializer {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DonorRepository donorRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public DataBaseInitializer(DoctorRepository doctorRepository, UserRepository userRepository, DonorRepository donorRepository, LocationRepository locationRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        User admin1 = new User(
                "admin",
                "admin",
                Role.ADMIN
        );
        userRepository.save(admin1);
        Doctor doctor1 = new Doctor(
                "doctor@gmail.com",
                "doctor",
                "Uilcan",
                "Vasile",
                "2312312312312",
                "Alba"
        );
        doctorRepository.save(doctor1);
        Doctor doctor2 = new Doctor(
                "docto",
                "doctor123",
                "Pavaluc",
                "Ion",
                "567899009888",
                "Hunedoara"
        );
        doctorRepository.save(doctor2);
        Doctor doctor3 = new Doctor(
                "healthy@yahoo.com",
                "suntbinemersi",
                "Sergheievici",
                "Alexandru",
                "567899009888",
                "Cluj-Napoca"
        );
        doctorRepository.save(doctor3);
        Donor donor1 = new Donor(
                "donor@gmail.com",
                "donor",
                "Costea",
                "Gabriel",
                "Cluj"
        );
        donorRepository.save(donor1);

        Location location = new Location(
                "Regina Maria",
                "str.Louis Pasteur nr.41",
                LocalTime.of(9,0),
                LocalTime.of(18,0),
                DayOfWeek.MONDAY,
                DayOfWeek.FRIDAY,
                100,
                "Cluj"
        );

        Location location1 = new Location(
                "Synevo",
                "str.1 Decembrie 1918, nr.41",
                LocalTime.of(8,0),
                LocalTime.of(20,0),
                DayOfWeek.MONDAY,
                DayOfWeek.WEDNESDAY,
                100,
                "Alba"
        );

        locationRepository.save(location);
        locationRepository.save(location1);

    }
}
