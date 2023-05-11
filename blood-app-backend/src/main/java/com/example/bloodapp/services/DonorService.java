package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.DeleteDonorResponse;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.controllers.dtos.DonorResponse;
import com.example.bloodapp.domain.entity.Donor;
import com.example.bloodapp.domain.repositories.DonorRepository;
import com.example.bloodapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DonorService {
    private final DonorRepository donorRepository;

    @Autowired
    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }


    public DonorResponse saveDonor(DonorResponse donorResponse) {
        Optional<Donor> optionalDonorResponse = donorRepository.findByEmail(donorResponse.getEmail());

        if (optionalDonorResponse.isPresent()) {
            throw new DuplicateEntityException("Duplicate donor acccount with email: " + optionalDonorResponse.get().getEmail());
        }
        Donor newDonor = new Donor(donorResponse);
        newDonor = donorRepository.save(newDonor);
        return new DonorResponse(newDonor);
    }

    public DonorResponse editDonor(Long id, DonorResponse donorResponse) {
        Optional<Donor> donors = donorRepository.findById(id);
        if(donors.isEmpty()){
            throw new NotFoundException("Donor with inserted id not found !");
        }
        Donor donor = donors.get();
        donor.setEmail(donorResponse.getEmail());
        donor.setPassword(donorResponse.getPassword());
        donor.setName(donorResponse.getName());
        donor.setSurname(donorResponse.getSurname());
        donor.setCounty(donorResponse.getCounty());
        donorRepository.save(donor);
        return new DonorResponse(donor);
    }

    public DeleteDonorResponse deleteDonor(Long id) {
        Optional<Donor> donors = donorRepository.findById(id);
        if(donors.isEmpty()){
            throw new NotFoundException("Donor with inserted id not found!");
        }
        Donor donor = donors.get();
        donorRepository.delete(donor);
        return new DeleteDonorResponse(donor.getId());
    }

    public DonorResponse findByID(Long id) {
        Optional<Donor> donors = donorRepository.findById(id);
        if(donors.isEmpty()){
            throw new NotFoundException("Donor with inserted id not found!");
        }
        Donor donor = donors.get();

        return new DonorResponse(donor);
    }
}
