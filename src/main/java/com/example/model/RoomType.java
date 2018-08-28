package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roomtype")
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "type_id")
	private int type_id;
	@Column(name = "number")
	@NotNull
	private int number;
	@Column(name = "type")
	@NotNull
	private String type;
	@Column(name="description")
	@NotNull(message = "*Please provide description")
	private String description;
	@Column(name = "price")
	@NotNull
	private Double price;

	public RoomType() {
		super();
	}
	


	public RoomType(@NotNull int number, @NotNull String type,
			@NotNull(message = "*Please provide description") String description, @NotNull Double price) {
		super();
		this.number = number;
		this.type = type;
		this.description = description;
		this.price = price;
	}


	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
}
