package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Role;
import com.example.model.Users;
import com.example.service.RoleService;
import com.example.service.UserService;

@Controller
@PreAuthorize("hasAnyAuthority('Admin')")
@RequestMapping(value = "/admin/user")
public class UserController {

	@Autowired
	UserService uS;
	@Autowired
	RoleService rS;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showList() {
		ModelAndView model = new ModelAndView("userList");
		List<Users> userList = uS.getAllUser();
		model.addObject("userList", userList);

		return model;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView model = new ModelAndView();
		Users user = new Users();
		List<Role> roleList = rS.getAllRole();
		model.addObject("userForm", user);
		model.addObject("roleList", roleList);
		model.setViewName("userForm");
		return model;
	}

	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Users user = uS.getUserById(id);
		List<Role> roleList = rS.getAllRole();
		model.addObject("roleList", roleList);
		model.addObject("userForm", user);
		model.setViewName("userForm");
		return model;
	}

	@RequestMapping(value = "/updateEdited", method = RequestMethod.POST)
	public ModelAndView updateEdited(@ModelAttribute("userForm") @Valid Users user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		List<Role> roleList = rS.getAllRole();
		model.addObject("roleList", roleList);
		if (result.hasErrors()) {
			model.setViewName("userForm");
		} else {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			uS.saveUser(user);
			model.setViewName("redirect:/admin/user/list");
		}
		return model;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("userForm") @Valid Users user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		List<Role> roleList = rS.getAllRole();
		model.addObject("roleList", roleList);
		Users userExists = uS.getUserByEmail(user.getEmail());
		if (userExists != null) {
			result.rejectValue("email", "error.user", "*There is already a user registered with the email provided");
		}
		if (result.hasErrors()) {
			model.setViewName("userForm");
		} else {
			uS.saveUser(user);
			model.setViewName("redirect:/admin/user/addUser?success=true");
		}
		return model;
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		uS.deleteUser(id);
		return new ModelAndView("redirect:/admin/user/list");
	}
	//
	// @RequestMapping(value = "/searchByEmail/{email}", method = RequestMethod.GET)
	// public ModelAndView searchUserByEmail(@PathVariable("searchValue") String
	// email) {
	// ModelAndView model = new ModelAndView("userList");
	// List<User> userList = uS.getAllUser();
	// List<Role> roleList = rS.getAllRole();
	// List<User> searchedUser = userList.stream().filter(s ->
	// s.getEmail().toLowerCase().contains(email.toLowerCase()))
	// .collect(Collectors.toList());
	// model.addObject("userList", searchedUser);
	// model.addObject("roleList", roleList);
	// return model;
	// }

}
