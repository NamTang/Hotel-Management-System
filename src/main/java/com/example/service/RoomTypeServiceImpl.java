package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.RoomType;
import com.example.repository.RoomTypeRepository;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	RoomTypeRepository rTR;

	@Override
	public List<RoomType> getAllRoomType() {
		return (List<RoomType>) rTR.findAll();
	}

	@Override
	public RoomType getRoomType(int id) {
		return rTR.findById(id).get();
	}

	@Override
	public void saveRoomType(RoomType roomType) {
		rTR.save(roomType);

	}

	@Override
	public void deleteRoomType(int id) {
		rTR.deleteById(id);

	}


}
