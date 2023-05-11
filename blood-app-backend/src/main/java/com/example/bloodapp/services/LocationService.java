package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.ListLocationResponse;
import com.example.bloodapp.controllers.dtos.LocationResponse;
import com.example.bloodapp.domain.entity.Donor;
import com.example.bloodapp.domain.entity.Location;
import com.example.bloodapp.domain.repositories.DonorRepository;
import com.example.bloodapp.domain.repositories.LocationRepository;
import com.example.bloodapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final DonorRepository donorRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository, DonorRepository donorRepository) {
        this.locationRepository = locationRepository;
        this.donorRepository = donorRepository;
    }

    public ListLocationResponse getAll(Long id) {
        Optional<Donor> donor = donorRepository.findById(id);
        if(donor.isEmpty()){
            throw new NotFoundException("Donor not found!");
        }
        Donor foundDonor = donor.get();
        List<Location> locations = locationRepository.findAll();
        List<LocationResponse> locationResponses = new ArrayList<>();

        for (Location l : locations) {
            if(l.getCounty().equals(foundDonor.getCounty())){
                locationResponses.add(new LocationResponse(l));
            }

        }
        return new ListLocationResponse(locationResponses);
    }
}
