package com.example.dentalClinic.repository;

import java.util.List;

import com.example.dentalClinic.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    List<Appointment> findAllByDentistProfileOrderByDateDesc(DentistProfile dentistProfile);
    List<Appointment> findAllByUserOrderByDateDesc(User user);
    Appointment findById(int id);

}
