package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	Room findByFloorAndNumber(int floor, int number);
}
