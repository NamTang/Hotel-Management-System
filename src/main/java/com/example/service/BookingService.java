package com.example.service;

import java.util.List;

import com.example.model.Booking;


public interface BookingService {

	public List<Booking> getAllBooking();

	public Booking getBooking(int id);

	public void saveBooking(Booking booking);

	public void deleteBooking(int id);
}
