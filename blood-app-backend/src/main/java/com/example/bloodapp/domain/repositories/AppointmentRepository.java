package com.example.bloodapp.domain.repositories;

import com.example.bloodapp.domain.entity.Appointment;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDonor_Id(Long id);
    List<Appointment> findByLocation_Id(Long id);
    List<Appointment> findByLocation_Id(Long id, Pageable pageable);
    List<Appointment> findAllByLocation_IdAndAndAppoinmentDate(Long location_id, LocalDate date);
    List<Appointment> findAllByAppoinmentDate(LocalDate date);
    Integer countAppointmentsByAppoinmentDateAndLocation_Id(LocalDate localDate, Long id);
    Integer countAppointmentsByAppoinmentDate(LocalDate localDate);
    Integer countAppointmentsByDonor_IdAndLocation_IdAndConfirmed(Long donorId, Long locationID, Boolean confirmed);
}
