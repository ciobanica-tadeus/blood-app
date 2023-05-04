package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.ListLocationResponse;
import com.example.bloodapp.controllers.dtos.LocationResponse;
import com.example.bloodapp.domain.entity.Location;
import com.example.bloodapp.domain.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public ListLocationResponse getAll() {
        List<Location> locations = locationRepository.findAll();
        List<LocationResponse> locationResponses = new ArrayList<>();

        for (Location l : locations) {
            locationResponses.add(new LocationResponse(l));
        }
        return new ListLocationResponse(locationResponses);
    }
}
