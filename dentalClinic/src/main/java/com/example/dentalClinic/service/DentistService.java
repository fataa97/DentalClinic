package com.example.dentalClinic.service;

import com.example.dentalClinic.model.*;

import java.util.List;

public interface DentistService {
	   public DentistProfile findDentistByUser(User user);
	    public DentistProfile findDentistById(int id);
	    public List<DentistProfile> findAllDentist();
	    public void saveDentist(DentistProfile dentist, User user);
}
