package com.example.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Booking;
import com.example.model.Users;
import com.example.service.RoleService;
import com.example.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService uS;

	@Autowired
	RoleService rS;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView model = new ModelAndView();
		Booking booking = new Booking();
		model.addObject("bookingForm", booking);
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginForm");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			model.setViewName("redirect:/");
		} else {
			Users user = new Users();
			model.addObject("registrationForm", user);
			model.setViewName("registrationForm");
		}

		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute("registrationForm") @Valid Users user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Users userExists = uS.getUserByEmail(user.getEmail());
		if (userExists != null) {
			result.rejectValue("email", "error.user", "*There is already a user registered with the email provided");
		}
		if (result.hasErrors()) {
			model.setViewName("registrationForm");
		} else {
			user.setRole(rS.getByRole("Customer"));
			uS.saveUser(user);
			model.setViewName("redirect:/");
		}
		return model;
	}

	@PreAuthorize("hasAnyAuthority('Admin', 'Clerk')")
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView model = new ModelAndView();
		model.setViewName("dashBoard");
		return model;
	}

	@PreAuthorize("hasAnyAuthority('Admin', 'Clerk')")
	@RequestMapping(value = "/admin/getCurrentAdmin", method = RequestMethod.GET)
	public @ResponseBody Users getCurrentAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = uS.getUserByEmail(auth.getName());
		return user;
	}

}
