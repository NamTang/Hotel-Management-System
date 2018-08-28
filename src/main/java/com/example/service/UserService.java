package com.example.service;

import java.util.List;
import com.example.model.Users;


public interface UserService {

	public List<Users> getAllUser();
	public Users getUserById(int id);
	public Users getUserByEmail(String email);
	public void saveUser(Users user);
	public void deleteUser(int id);
}