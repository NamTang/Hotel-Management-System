package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Room;
import com.example.model.RoomType;
import com.example.service.RoomService;
import com.example.service.RoomTypeService;

@Controller
@PreAuthorize("hasAnyAuthority('Admin', 'Clerk')")
@RequestMapping(value = "/admin/room")
public class RoomController {
	@Autowired
	RoomService rS;

	@Autowired
	RoomTypeService rTS;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showList() {
		ModelAndView model = new ModelAndView();
		List<Room> roomList = rS.getAllRoom();
		model.addObject("roomList", roomList);
		model.setViewName("roomList");
		
		return model;
	}

	/*
	 * CRUD Room Methods.
	 */
	@RequestMapping(value = "/addRoom", method = RequestMethod.GET)
	public ModelAndView addRoom() {
		ModelAndView model = new ModelAndView();
		Room room = new Room();
		List<RoomType> roomTypeList = rTS.getAllRoomType();
		model.addObject("roomTypeList", roomTypeList);
		model.addObject("roomForm", room);
		model.setViewName("roomForm");
		return model;
	}

	@RequestMapping(value = "/editRoom/{id}", method = RequestMethod.GET)
	public ModelAndView editRoom(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Room room = rS.getRoom(id);
		List<RoomType> roomTypeList = rTS.getAllRoomType();
		model.addObject("roomTypeList", roomTypeList);
		model.addObject("roomForm", room);
		model.setViewName("roomForm");
		return model;
	}

	@RequestMapping(value = "/saveRoom", method = RequestMethod.POST)
	public ModelAndView saveRoom(@ModelAttribute("roomForm") @Valid Room room, BindingResult result) {
		ModelAndView model = new ModelAndView();
		List<RoomType> roomTypeList = rTS.getAllRoomType();
		model.addObject("roomTypeList", roomTypeList);
		Room exists = rS.getRoomByFloorAndNumber(room.getFloor(), room.getNumber());
		if (exists != null) {
			result.rejectValue("number", "error.number", " *This room already exists!");
		}
		if (result.hasErrors()) {
			model.setViewName("roomForm");
		} else {
			rS.saveRoom(room);
			model.setViewName("redirect:/admin/room/addRoom?success=true");
		}
		return model;
	}

	@RequestMapping(value = "/updateEdited", method = RequestMethod.POST)
	public ModelAndView updateEditedRoom(@ModelAttribute("userForm") @Valid Room room, BindingResult result) {
		ModelAndView model = new ModelAndView();
		List<RoomType> roomTypeList = rTS.getAllRoomType();
		model.addObject("roomTypeList", roomTypeList);
		if (result.hasErrors()) {
			model.setViewName("userForm");
		} else {
			rS.saveRoom(room);
			model.setViewName("redirect:/admin/room/list");
		}
		return model;
	}

	@RequestMapping(value = "/deleteRoom/{id}", method = RequestMethod.GET)
	public ModelAndView deleteRoom(@PathVariable("id") int id) {
		rS.deleteRoom(id);
		return new ModelAndView("redirect:/admin/room/list");
	}

}
