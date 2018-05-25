package com.example.dentalClinic.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Transient;
import java.io.Serializable;

@Entity
@Table(name = "dentist")
public class DentistProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dentist_id")
	private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	@Column(name = "phone_number")
	@Size(max = 15)
	private String phoneNumber;
    @Column(length = 10)
	private String gender;
	@Size(max = 500)
	private String img;
	@Size(max = 100)
	private String address1;
	@Size(max = 100)
	private String address2;
	@Size(max = 100)
	private String city;
	@Size(max = 100)
	private String country;
	@Column(name = "zip_code")
	@Size(max = 32)
	private String zipCode;
	@Column(length = 100)
	private String area;
	 
	public DentistProfile() {}

	public DentistProfile(User user, String phoneNumber, String gender, String img, String address1,
	                         String address2, String city, String country,
	                         String zipCode, String area) {
	        this.user = user;
	        this.phoneNumber = phoneNumber;
	        this.gender = gender;
	        this.img = img;
	        this.address1 = address1;
	        this.address2 = address2;
	        this.city = city;
	        this.country = country;
	        this.zipCode = zipCode;
	        this.area = area;
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
	public String getPhoneNumber() {
        return phoneNumber;
    }

	public String setPhoneNumber() {
        return phoneNumber;
    }

	public String getGender() {
		return gender;
		}
	
	public void setGender(String gender) {
        this.gender = gender;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}