package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Images_RoomType;
import com.example.model.RoomType;
import com.example.repository.Images_RoomTypeRepository;

@Service
public class Images_RoomTypeServiceImpl implements Images_RoomTypeService {

	@Autowired
	Images_RoomTypeRepository imgR;

	@Autowired
	RoomTypeService rS;

	private String[] imageTypes = { "image/jpeg", "image/png", "image/gif", "image/webp", "image/tif", "image/bmp", "image/jxr", "image/psd" };

	@Override
	public List<Images_RoomType> getAllImages() {
		return (List<Images_RoomType>) imgR.findAll();
	}

	@Override
	public Images_RoomType getImageById(int id) {
		return imgR.findById(id).get();
	}

	@Override
	public void saveImage(Images_RoomType image) {
		imgR.save(image);
	}

	@Override
	public void deleteImage(int id) {
		imgR.deleteById(id);
	}

	@Override
	public List<Images_RoomType> getListImagesByTypeId(int type_id) {
		RoomType roomType = rS.getRoomType(type_id);
		return imgR.findByRoomType(roomType);
	}

	@Override
	public Boolean isImage(String type) {
		for (String s : imageTypes) {
			if (type.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

}
