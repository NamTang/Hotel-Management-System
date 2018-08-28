package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository uR;

	@Override
	public List<Users> getAllUser() {

		return (List<Users>) uR.findAll();
	}

	@Override
	public Users getUserById(int id) {
		return uR.findById(id).get();
	}

	@Override
	public void saveUser(Users user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setActive(1);
		uR.save(user);

	}

	@Override
	public void deleteUser(int id) {
		uR.deleteById(id);
	}

	@Override
	public Users getUserByEmail(String email) {
		return uR.findUserByEmail(email);
	}

}
