package soen343.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import soen343.backend.house.House;
import soen343.backend.house.HouseService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
		new SimulationMasterController();
	}

	@Bean
	CommandLineRunner runner(HouseService houseService) {
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<House>> typeReference = new TypeReference<List<House>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/houseLayout.json");
			try {
				List<House> rooms = mapper.readValue(inputStream, typeReference);
				houseService.save(rooms);
			} catch (IOException e) {
				System.out.println("Unable to save house layout: " + e.getMessage());
			}
		};
	}
}
