package com.example.dentalClinic.service;

import java.util.List;

import com.example.dentalClinic.model.*;
import com.example.dentalClinic.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dentistService")
public class DentistServiceImpl implements DentistService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DentistProfile findDentistByUser(User user) {
        return dentistRepository.findByUser(user);
    }

    @Override
    public List<DentistProfile> findAllDentist() {
        return dentistRepository.findAll();
    }

    @Override
    public DentistProfile findDentistById(int id) {
        return dentistRepository.findById(id);
    }

    @Override
    public void saveDentist(DentistProfile dentist, User user) {
        Role userRole = roleRepository.findByRole("DENTIST");
        user.getRoles().add(userRole);
        user.setDentistProfile(dentist);
        dentist.setUser(user);
        userRepository.save(user);
    }
}
