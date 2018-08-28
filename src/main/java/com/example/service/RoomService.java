package com.example.service;

import java.util.List;

import com.example.model.Room;

public interface RoomService {
	public List<Room> getAllRoom();
	
	public Boolean isExistsRoom(int type_id);

	public Room getRoom(int id);

	public void saveRoom(Room room);

	public void deleteRoom(int id);
	
	public Room getRoomByFloorAndNumber(int floor, int number);
}
