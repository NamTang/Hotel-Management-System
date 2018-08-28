package com.example.service;

import java.util.List;

import com.example.model.Role;

public interface RoleService {

	public List<Role> getAllRole();

	public Role getRoleById(int id);

	public void saveRole(Role role);

	public void deleteRole(int id);
	
	public Role getByRole(String role);
}
