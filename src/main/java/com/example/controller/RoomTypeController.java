package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.model.RoomType;
import com.example.service.RoomService;
import com.example.service.RoomTypeService;

@Controller
@PreAuthorize("hasAnyAuthority('Admin', 'Clerk')")
@RequestMapping(value = "/admin/roomType")
public class RoomTypeController {

	@Autowired
	RoomService rS;

	@Autowired
	RoomTypeService rTS;
	


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showList() {
		ModelAndView model = new ModelAndView();
		List<RoomType> roomTypeList = rTS.getAllRoomType();
		model.addObject("roomTypeList", roomTypeList);
		model.setViewName("roomTypeList");
		return model;
	}

	/**
	 * 
	 * CRUD Room Type Methods.
	 */
	@RequestMapping(value = "/addRoomType", method = RequestMethod.GET)
	public ModelAndView addRoomType() {
		ModelAndView model = new ModelAndView();
		RoomType roomType = new RoomType();
		model.addObject("roomTypeForm", roomType);
		model.setViewName("roomTypeForm");
		return model;
	}

	@RequestMapping(value = "/editRoomType/{roomtype_id}", method = RequestMethod.GET)
	public ModelAndView editRoomType(@PathVariable("roomtype_id") int id) {
		ModelAndView model = new ModelAndView();
		RoomType roomType = rTS.getRoomType(id);
		model.addObject("roomTypeForm", roomType);
		model.setViewName("roomTypeForm");
		return model;
	}

	@RequestMapping(value = "/saveRoomType", method = RequestMethod.POST)
	public ModelAndView saveRoomType(@ModelAttribute("roomTypeForm") RoomType roomType) {
		rTS.saveRoomType(roomType);
		return new ModelAndView("redirect:/admin/roomType/list");
	}

	@RequestMapping(value = "/deleteRoomType/{roomtype_id}", method = RequestMethod.GET)
	public ModelAndView deleteType(@PathVariable("roomtype_id") int id) {
		ModelAndView model = new ModelAndView();
		if (rS.isExistsRoom(id)) {
			model.setViewName("redirect:/admin/roomType/list?deleteTypeError=true");
		} else {
			rTS.deleteRoomType(id);
			model.setViewName("redirect:/admin/roomType/list");
		}
		return model;
	}
}
