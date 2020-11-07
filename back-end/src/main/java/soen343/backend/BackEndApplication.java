package soen343.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import soen343.backend.room.*;
import soen343.backend.user.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class BackEndApplication {
	public static SimulationMasterController sMC;

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
		sMC = new SimulationMasterController();
		System.out.println("Simulation master controller running");
	}

	@Bean
	CommandLineRunner runner(RoomService roomService) {
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/houseLayout.json");
			try {
				List<Room> rooms = mapper.readValue(inputStream, typeReference);
				roomService.save(rooms);
			} catch (IOException e) {
				System.out.println("Unable to save house layout: " + e.getMessage());
			}
		};
	}
	
	@Bean
	CommandLineRunner userRunner(UserService userService) {
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			try {
				List<User> users = mapper.readValue(inputStream, typeReference);
				userService.save(users);
			} catch (IOException e) {
				System.out.println("Unable to save userlist: " + e.getMessage());
			}
		};
	}
	
	//@Scheduled(fixedRate = 1000)
	//public static void updateSimulationDateTime() {
	//	SimulationMasterController.updateSimulationDateTime();
	//}
	
	
}
