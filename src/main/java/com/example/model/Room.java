package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private int room_id;
	@Column(name = "floor")
	@NotNull(message = "*Please provide what floor")
	@Min(value = 0L, message = "The value must be positive")
	private int floor;
	@Column(name = "number")
	@NotNull(message = "*Please provide what room's number")
	@Min(value = 0L, message = "The value must be positive")
	private int number;
	@Column(name = "roomstatus")
	private String roomstatus;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private RoomType roomType;

	public Room() {
		super();
	}
	
	

	public Room(int room_id, @NotNull(message = "*Please provide what floor") int floor,
			@NotNull(message = "*Please provide what room's number") int number, String roomstatus, RoomType roomType) {
		super();
		this.room_id = room_id;
		this.floor = floor;
		this.number = number;
		this.roomstatus = roomstatus;
		this.roomType = roomType;
	}



	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getRoomstatus() {
		return roomstatus;
	}

	public void setRoomstatus(String roomstatus) {
		this.roomstatus = roomstatus;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

}
