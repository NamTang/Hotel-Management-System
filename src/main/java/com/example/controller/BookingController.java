package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Booking;

import com.example.service.BookingService;

@Controller
public class BookingController {
	@Autowired
	BookingService bS;

	@RequestMapping(value = "/booking/list", method = RequestMethod.GET)
	public ModelAndView showList() {
		ModelAndView model = new ModelAndView("bookingList");
		List<Booking> bookingList = bS.getAllBooking();
		model.addObject("bookingList", bookingList);
		return model;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ModelAndView addBooking() {
		ModelAndView model = new ModelAndView();
		Booking booking = new Booking();
		model.addObject("bookingForm", booking);
		model.setViewName("bookingForm");
		return model;
	}

}
