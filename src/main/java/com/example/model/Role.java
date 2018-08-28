package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int role_id;

	@Column(name = "role")
	private String role;

	public Role() {
		super();
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public int getRole_id() {
		return role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public void setRole(String role) {
		this.role = role;
	}

}