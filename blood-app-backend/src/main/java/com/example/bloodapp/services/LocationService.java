package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.ListLocationResponse;
import com.example.bloodapp.controllers.dtos.LocationResponse;
import com.example.bloodapp.controllers.dtos.RatingResponse;
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


    public RatingResponse setNewRatingValue(RatingResponse ratingResponse) {
        if(ratingResponse.getRatingValue() > 10 && ratingResponse.getRatingValue() < 0){
            throw new NotFoundException("Insert values beetween 0-10!!");
        }
        Optional<Location> optionalLocation = locationRepository.findById(ratingResponse.getLocationId());
        if(optionalLocation.isEmpty()){
            throw new NotFoundException("Location with inserted id not found!!");
        }
        Location location = optionalLocation.get();
        location.addRating(ratingResponse.getRatingValue());
        List<Integer> ratings = location.getRatings();
        Integer sum = 0;
        for (Integer rating : ratings) {
            sum += rating;
        }
        Float averageValue = (float) (sum / ratings.size());
        location.setRatings(ratings);
        location.setAverageRating(averageValue);
        locationRepository.save(location);
        return new RatingResponse(averageValue);
    }

    public RatingResponse getRating(Long id) {
        Optional<Location> locations = locationRepository.findById(id);
        if(locations.isEmpty()){
            throw new NotFoundException("Location with inserted id not found!!");
        }
        Location location = locations.get();
        return new RatingResponse(location.getAverageRating());
    }
}
