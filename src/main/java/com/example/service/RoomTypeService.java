package com.example.service;

import java.util.List;

import com.example.model.RoomType;

public interface RoomTypeService {
	public List<RoomType> getAllRoomType();
	
	public RoomType getRoomType(int id);

	public void saveRoomType(RoomType roomType);

	public void deleteRoomType(int id);
}
