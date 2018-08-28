package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Booking;
import com.example.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{
	@Autowired
	BookingRepository rR;

	@Override
	public List<Booking> getAllBooking() {

		return (List<Booking>) rR.findAll();
	}

	@Override
	public Booking getBooking(int id) {
		return rR.findById(id).get();
	}

	@Override
	public void saveBooking(Booking booking) {
		rR.save(booking);

	}

	@Override
	public void deleteBooking(int id) {
		rR.deleteById(id);
	}
}
