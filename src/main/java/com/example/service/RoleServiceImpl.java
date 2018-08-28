package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository rR;

	@Override
	public List<Role> getAllRole() {

		return (List<Role>) rR.findAll();
	}

	@Override
	public Role getRoleById(int id) {
		return rR.findById(id).get();
	}

	@Override
	public void saveRole(Role role) {
		rR.save(role);

	}

	@Override
	public void deleteRole(int id) {
		rR.deleteById(id);
	}

	@Override
	public Role getByRole(String role) {
		return rR.findByRole(role);
	}
}
