package com.example.dentalClinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.dentalClinic.model.DentistProfile;
import com.example.dentalClinic.model.User;



@Entity
@Table(name = "appointment")
public class Appointment{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentist_id", nullable = false)
    private DentistProfile dentistProfile;
    @Size(max = 32)
    private String date;
    @Column(name = "start_time")
    private String start;
    @Column(name = "end_time")
    private String end;
    @Column
    private String price;

    public Appointment() {}

    public Appointment(User patient, DentistProfile dentist, String date, String start_time, String end_time) {
        this.user = patient;
        this.dentistProfile = dentist;
        this.date = date;
        this.start = start_time;
        this.end = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DentistProfile getDentistProfile() {
        return dentistProfile;
    }

    public void setDentistProfile(DentistProfile dentistProfile) {
        this.dentistProfile = dentistProfile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}