package com.example.dentalClinic.service;

import java.util.List;

import com.example.dentalClinic.model.*;

public interface AppointmentService {
	public Appointment findAppointmentById (int id);
    public List<Appointment> findAppointmentsByUser (User user);
    public List<Appointment> findAppointmentsByDentist (DentistProfile dentist);
    public void saveAppointment(Appointment appointment, DentistProfile dentist, User user);
    public void updateAppointment(Appointment appointment, String price);
}
