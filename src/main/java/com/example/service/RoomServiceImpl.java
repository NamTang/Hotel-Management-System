package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Room;
import com.example.repository.RoomRepository;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository rR;

	@Override
	public List<Room> getAllRoom() {

		return (List<Room>) rR.findAll();
	}

	@Override
	public Room getRoom(int id) {
		return rR.findById(id).get();
	}

	@Override
	public void saveRoom(Room room) {
		rR.save(room);

	}

	@Override
	public void deleteRoom(int id) { 
		rR.deleteById(id);
	}

	@Override
	public Room getRoomByFloorAndNumber(int floor, int number) {
		return rR.findByFloorAndNumber(floor, number);
	}

	@Override
	public Boolean isExistsRoom(int type_id) {
		for(Room r : getAllRoom()) {
			if(r.getRoomType().getType_id() == type_id) {
				return true;
			}
		}
		return false;
	}

}
