package com.example.dentalClinic.controller;

import java.util.List;

import com.example.dentalClinic.model.*;
import com.example.dentalClinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DentistController {
    @Autowired
    private UserService userService;

    @Autowired
    private DentistService dentistService;

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

    @RequestMapping(value = {"/dentists"}, method = RequestMethod.GET)
    public ModelAndView findAllDoctors() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<DentistProfile> dentistList = dentistService.findAllDentist();
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Clinic");
        modelAndView.addObject("dentistList", dentistList);
        modelAndView.addObject("Message", "Find Your Dentist");
        modelAndView.setViewName("dentist/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/dentist/{id}"}, method = RequestMethod.GET)
    public ModelAndView findDoctorDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DentistProfile dentist = dentistService.findDentistById(id);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Clinic");
        modelAndView.addObject("Message", "Find Your Dentist");
        modelAndView.addObject("dentistName", dentist.getUser().getName() + " " + dentist.getUser().getLastName());
        modelAndView.addObject("dentistAddressLine1", dentist.getAddress1() + ", " + dentist.getAddress2());
        modelAndView.addObject("dentistAddressLine2", dentist.getCity() + ", "  + dentist.getCountry() + ", " + dentist.getZipCode());
        modelAndView.addObject("dentist", dentist);
        modelAndView.setViewName("dentist/detail");
        return modelAndView;
    }
}
