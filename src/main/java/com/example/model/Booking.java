package com.example.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booking_id")
	private int booking_id;
	@Column(name = "bookedtime", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookedtime;
	@Column(name = "numberOfCustomer")
	private int numberOfCustomer;
	@Column(name = "fromDate")
	private Date fromDate;
	@Column(name = "toDate")
	private Date toDate;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public Booking() {
		super();
	}

	public Booking(int booking_id, int numberOfCustomer, Date fromDate, Date toDate, Room room,
			Users user) {
		super();
		this.booking_id = booking_id;
		this.numberOfCustomer = numberOfCustomer;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.room = room;
		this.user = user;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public Date getBookedtime() {
		return bookedtime;
	}

	public void setBookedtime(Date bookedtime) {
		this.bookedtime = bookedtime;
	}

	public int getNumberOfCustomer() {
		return numberOfCustomer;
	}

	public void setNumberOfCustomer(int numberOfCustomer) {
		this.numberOfCustomer = numberOfCustomer;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
