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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class BackEndApplication {
	public static SimulationMasterController sMC;

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
		sMC = new SimulationMasterController();
		System.out.println("Simulation master controller running");
		
		ObjectMapper map =new ObjectMapper();
		Staff staff=createStaff();
		try 
		{
			map.writeValue(new File("c:\\test\\staff.jason"), staff);
			
			String jsonString=map.writeValueAsString(staff);
			System.out.println(jsonString);
			String jsonInString2=map.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
			System.out.println(jsonInString2);	
		}catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static Staff createStaff() 
	{
		Staff staff=new Staff();
		staff.setName("Laurent");
		staff.setAge(34);
		staff.setPosition(new String[] {"Developper","CEO"});
		staff.setSkills(Arrays.asList("java","python","node"));
		return staff;
		
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
	
	//@Scheduled(fixedRate = 1000)
	//public static void updateSimulationDateTime() {
	//	SimulationMasterController.updateSimulationDateTime();
	//}
	
	
}
