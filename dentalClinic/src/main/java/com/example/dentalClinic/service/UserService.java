package com.example.dentalClinic.service;

import com.example.dentalClinic.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User findById(int id);
	public void saveUser(User user);
}
