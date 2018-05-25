package com.example.dentalClinic.repository;

import com.example.dentalClinic.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("dentistRepository")
public interface DentistRepository extends JpaRepository<DentistProfile, Integer>{
	DentistProfile findByUser(User user);
    DentistProfile findById(int id);
    List<DentistProfile> findAll();
}
