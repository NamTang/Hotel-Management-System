package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Images_RoomType;
import com.example.model.RoomType;
import com.example.service.Images_RoomTypeService;
import com.example.service.RoomTypeService;

@Controller
@PreAuthorize("hasAnyAuthority('Admin', 'Clerk')")
@RequestMapping(value = "/admin/roomType")
public class ImageController {

	@Autowired
	Images_RoomTypeService imageS;
	@Autowired
	RoomTypeService rTS;

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("image_id") int image_id, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException {

		Images_RoomType item = imageS.getImageById(image_id);
		response.setContentType(item.getType());
		response.getOutputStream().write(item.getPicture());
		response.getOutputStream().close();
	}

	@RequestMapping(value = "/images/{type_id}")
	public ModelAndView list(@PathVariable int type_id) {
		ModelAndView model = new ModelAndView();
		RoomType roomType = rTS.getRoomType(type_id);
		List<Images_RoomType> imageList = imageS.getListImagesByTypeId(type_id);
		model.addObject("roomType", roomType);
		model.addObject("imageList", imageList);
		model.setViewName("roomType_imageList");

		return model;
	}

	@RequestMapping(value = "images/uploadPicture", method = RequestMethod.POST)
	public ModelAndView uploadImage(@RequestParam("type_id") int type_id, @RequestParam("file") MultipartFile file)
			throws IOException {
		ModelAndView model = new ModelAndView();
		Images_RoomType img = new Images_RoomType();
		if (file.isEmpty()) {
			model.setViewName("roomType_imageList");
		}
		System.out.println(file.getContentType().toString());
		if (imageS.isImage(file.getContentType().toString())) {
			img.setPicture(file.getBytes());
			img.setName(file.getOriginalFilename());
			img.setType(file.getContentType());
			img.setRoomType(rTS.getRoomType(type_id));
			imageS.saveImage(img);
			model.setViewName("redirect:/admin/roomType/images/" + type_id + "?upload=true");
		} else {
			model.setViewName("redirect:/admin/roomType/images/" + type_id + "?notImage=true");
		}

		return model;
	}

	@RequestMapping(value = "/deleteImage/{type_id}/{image_id}", method = RequestMethod.GET)
	public ModelAndView deleteImage(@PathVariable("type_id") int type_id, @PathVariable("image_id") int image_id) {
		imageS.deleteImage(image_id);
		return new ModelAndView("redirect:/admin/roomType/images/" + type_id);
	}
}
