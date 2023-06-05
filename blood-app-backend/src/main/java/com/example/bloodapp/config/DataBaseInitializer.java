package com.example.bloodapp.config;

import com.example.bloodapp.domain.entity.*;
import com.example.bloodapp.domain.repositories.LocationRepository;
import com.example.bloodapp.domain.repositories.UserRepository;
import com.example.bloodapp.domain.repositories.DoctorRepository;
import com.example.bloodapp.domain.repositories.DonorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@PostConstruct
    public void init() {
        Location location = new Location(
                "Regina Maria",
                "str.Louis Pasteur nr.41",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                DayOfWeek.MONDAY,
                DayOfWeek.FRIDAY,
                5,
                "Cluj"
        );
        Location location2 = new Location(
                "MedStar",
                "str.Marasti nr.1",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                DayOfWeek.MONDAY,
                DayOfWeek.FRIDAY,
                5,
                "Cluj"
        );

        Location location1 = new Location(
                "Synevo",
                "str.1 Decembrie 1918, nr.41",
                LocalTime.of(8, 0),
                LocalTime.of(20, 0),
                DayOfWeek.MONDAY,
                DayOfWeek.WEDNESDAY,
                5,
                "Alba"
        );

        Location location3 = new Location(
                "Holhos Medicine",
                "str.Ion Pantelimon nr.21",
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                DayOfWeek.MONDAY,
                DayOfWeek.THURSDAY,
                5,
                "Hunedoara"
        );

        locationRepository.save(location);
        locationRepository.save(location1);
        locationRepository.save(location2);
        locationRepository.save(location3);

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
                "Alba", location1
        );
        //doctor1.setLocation(location1);
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor(
                "docto",
                "doctor123",
                "Pavaluc",
                "Ion",
                "567899009888",
                "Hunedoara",
                location3
        );
        //doctor2.setLocation(location3);
        doctorRepository.save(doctor2);

        Doctor doctor3 = new Doctor(
                "healthy@yahoo.com",
                "donate",
                "Sergheievici",
                "Alexandru",
                "567899009888",
                "Cluj",
                location
        );
        //doctor3.setLocation(location);
        doctorRepository.save(doctor3);

        Donor donor1 = new Donor(
                "donor@gmail.com",
                "donor",
                "Costea",
                "Gabriel",
                "Cluj"
        );
        donorRepository.save(donor1);
        Donor donor2= new Donor(
                "diana@gmail.com",
                "imiplace",
                "Narita",
                "Diana Nicoleta",
                "Cluj"
        );
        donorRepository.save(donor2);
    }
}
