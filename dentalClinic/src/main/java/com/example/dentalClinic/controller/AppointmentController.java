package com.example.dentalClinic.controller;

import com.example.dentalClinic.model.*;
import com.example.dentalClinic.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.dentalClinic.service.UserService;


import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@Controller
public class AppointmentController {
	@Autowired
    private UserService userService;

    @Autowired
    private DentistService dentistService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    @Qualifier("userService")
    public void setClientService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    @Qualifier("dentistService")
    public void setClientService(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @Autowired
    @Qualifier("appointmentService")
    public void setClientService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = {"/appointment/{id}/new"}, method = RequestMethod.GET)
    public ModelAndView makeAppointment(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DentistProfile dentist = dentistService.findDentistById(id);
        Appointment appointment = new Appointment();
        modelAndView.addObject("appointment", appointment);
        modelAndView.addObject("dentist", dentist);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.addObject("Message", "Make an appointment with " +   dentist.getUser().getName() + " " + dentist.getUser().getLastName());
        modelAndView.setViewName("appointment/new");
        return modelAndView;
    }

    @RequestMapping(value ={"/appointment/{id}/new"}, method = RequestMethod.POST)
    public ModelAndView createAppointment(@PathVariable("id") int id,
                                          @Valid Appointment appointment, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName().toString());
        User user = userService.findUserByEmail(auth.getName());
        DentistProfile dentist = dentistService.findDentistById(id);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("appointment/new");
        } else {
            appointmentService.saveAppointment(appointment, dentist, user);
            modelAndView.addObject("successMessage", "Appointment with "
                    + dentist.getUser().getName() + " " + dentist.getUser().getLastName()
                    + " has been registered successfully");
            modelAndView.addObject("user", user);
            modelAndView.addObject("dentist", dentist);
            modelAndView.addObject("appointment", new Appointment());
            modelAndView.setViewName("appointment/new");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/user/{id}/appointments"}, method = RequestMethod.GET)
    public ModelAndView findAllAppointmentsByUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Appointment> appointmentList = appointmentService.findAppointmentsByUser(user);
        modelAndView.addObject("appointmentList", appointmentList);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.setViewName("appointment/user");
        return modelAndView;
    }

    @RequestMapping(value = {"/patient/{id}/info"}, method = RequestMethod.GET)
    public ModelAndView findUserInfo(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User patient = userService.findById(id);
        List<Appointment> appointmentList = appointmentService.findAppointmentsByUser(patient);
        modelAndView.addObject("dentistId", user.getDentistProfile().getId());
        modelAndView.addObject("appointmentList", appointmentList);
        modelAndView.addObject("patient",patient);
        modelAndView.addObject("dentist",user);
        modelAndView.addObject("patientName", patient.getName() + " " + user.getLastName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.setViewName("user/info");
        return modelAndView;
    }

    @RequestMapping(value = {"/dentist/{id}/appointments"}, method = RequestMethod.GET)
    public ModelAndView findAllAppointmentsByDentist(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DentistProfile dentist = dentistService.findDentistById(id);
        List<Appointment> appointmentList = appointmentService.findAppointmentsByDentist(dentist);
        modelAndView.addObject("appointmentList", appointmentList);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Find your future appointments");
        modelAndView.setViewName("appointment/dentist");
        return modelAndView;
    }

    @RequestMapping(value={"/appointment/{aid}/setPrice"}, method = RequestMethod.GET)
    public ModelAndView setPriceForAppointment(@PathVariable("aid") int appointmentId) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DentistProfile dentist = user.getDentistProfile();
        Appointment appointment = appointmentService.findAppointmentById(appointmentId);
        modelAndView.addObject("appointment", appointment);
        modelAndView.addObject("dentist", dentist);
        modelAndView.addObject("patient", appointment.getUser());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.addObject("Message", "Assign Price Form");
        modelAndView.setViewName("appointment/price");
        return modelAndView;
    }

    @RequestMapping(value = {"/appointment/{aid}/setPrice"}, method = RequestMethod.PUT)
    public ModelAndView updateAppointment(@PathVariable("aid") int appointmentId,
                                          @Valid Appointment appointment, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DentistProfile dentist = user.getDentistProfile();
        Appointment appointment1 = appointmentService.findAppointmentById(appointmentId);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Can't Assign the Price. Try Again!");
            modelAndView.setViewName("appointment/price");
        } else {
            appointmentService.updateAppointment(appointment1, appointment.getPrice());
            modelAndView.addObject("successMessage", "Price has been assigned successfully");
            modelAndView.addObject("appointment", appointment1);
            modelAndView.addObject("dentist", dentist);
            modelAndView.addObject("patient", appointment.getUser());
            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("adminMessage", "Welcome To Dental Clinic");
            modelAndView.addObject("Message", "Assign Price Form");
            modelAndView.setViewName("appointment/price");
        }
        return modelAndView;
    }
}
