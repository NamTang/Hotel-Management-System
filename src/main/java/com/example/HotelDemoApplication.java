package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.model.Role;
import com.example.model.Room;
import com.example.model.RoomType;
import com.example.model.Users;
import com.example.repository.RoleRepository;
import com.example.repository.RoomRepository;
import com.example.repository.RoomTypeRepository;
import com.example.repository.UserRepository;

@SpringBootApplication
public class HotelDemoApplication implements CommandLineRunner {
	@Autowired
	UserRepository uR;
	@Autowired
	RoomTypeRepository rTR;
	@Autowired
	RoomRepository rR;
	@Autowired
	RoleRepository roleR;

	public static void main(String[] args) {

		SpringApplication.run(HotelDemoApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void run(String... args) throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(new Role("Admin"));
		roleList.add(new Role("Clerk"));
		roleList.add(new Role("Customer"));

		Users admin = new Users();
		admin.setActive(1);
		admin.setEmail("admin@gmail.com");
		admin.setLastName("Tang");
		admin.setName("Nam");
		admin.setRole(roleList.get(0));
		admin.setPassword(new BCryptPasswordEncoder().encode("kty0309"));

		Users clerk = new Users();
		clerk.setActive(1);
		clerk.setEmail("clerk@gmail.com");
		clerk.setLastName("Waston");
		clerk.setName("Lucas");
		clerk.setRole(roleList.get(1));
		clerk.setPassword(new BCryptPasswordEncoder().encode("bjh0329"));

		Users customer = new Users();
		customer.setActive(1);
		customer.setEmail("customer@gmail.com");
		customer.setLastName("Kim");
		customer.setName("T");
		customer.setRole(roleList.get(2));
		customer.setPassword(new BCryptPasswordEncoder().encode("03090329"));

		List<RoomType> roomTypeList = new ArrayList<RoomType>();
		roomTypeList.add(new RoomType(4, "Normal", "", 19.99));
		roomTypeList.add(new RoomType(8, "Suite", "", 69.99));
		roomTypeList.add(new RoomType(4, "VIP", "", 139.99));
		roomTypeList.add(new RoomType(12, "President Suite", "", 1000.0));

		List<Room> roomList = new ArrayList<Room>();
		roomList.add(new Room(1, 1, 7, "Available", roomTypeList.get(0)));
		roomList.add(new Room(2, 1, 8, "Available", roomTypeList.get(1)));
		roomList.add(new Room(3, 1, 9, "Available", roomTypeList.get(2)));
		roomList.add(new Room(5, 1, 10, "Available", roomTypeList.get(3)));

		for (Role r : roleList) {
			roleR.save(r);
		}

		uR.save(admin);
		uR.save(clerk);
		uR.save(customer);

		for (RoomType rt : roomTypeList) {
			rTR.save(rt);
		}

		for (Room room : roomList) {
			rR.save(room);
		}

	}

}
