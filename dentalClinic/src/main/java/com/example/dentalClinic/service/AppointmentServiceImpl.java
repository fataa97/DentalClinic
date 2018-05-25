package com.example.dentalClinic.service;

import com.example.dentalClinic.model.*;
import com.example.dentalClinic.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("appointmentService")
public class AppointmentServiceImpl  implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment findAppointmentById (int id) { return appointmentRepository.findById(id); }

    @Override
    public List<Appointment> findAppointmentsByUser (User user) { return appointmentRepository.findAllByUserOrderByDateDesc(user); }

    @Override
    public List<Appointment> findAppointmentsByDentist (DentistProfile dentist) {
        return appointmentRepository.findAllByDentistProfileOrderByDateDesc(dentist);
    }

    @Override
    public void saveAppointment(Appointment appointment, DentistProfile dentist, User user) {
        appointment.setDentistProfile(dentist);
        appointment.setUser(user);
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment, String price) {
        appointment.setPrice(price);
        appointmentRepository.save(appointment);
    }
}

