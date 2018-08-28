package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Images_RoomType;
import com.example.model.RoomType;

@Repository
public interface Images_RoomTypeRepository  extends JpaRepository<Images_RoomType, Integer>{
	
	List<Images_RoomType> findByRoomType(RoomType roomType);

}
