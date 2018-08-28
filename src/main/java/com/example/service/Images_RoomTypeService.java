package com.example.service;

import java.util.List;

import com.example.model.Images_RoomType;

public interface Images_RoomTypeService {

	public List<Images_RoomType> getAllImages();

	public List<Images_RoomType> getListImagesByTypeId(int type_id);

	public Images_RoomType getImageById(int id);

	public void saveImage(Images_RoomType image);

	public void deleteImage(int id);
	
	public Boolean isImage(String type);
}
