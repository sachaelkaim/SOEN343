package soen343.backend;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import soen343.backend.room.Room;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SimulationContextTest {
	
	
	/*Use case (2) and (7)
	@Test
	public void setDateTimeTest() {
		LocalDateTime simDateTime = LocalDateTime.of(2017, 2, 13, 15, 56);
		SimulationMasterController.setSimulationDateTime(simDateTime);
		assertEquals(simDateTime, SimulationMasterController.getSimulationDateTime());
	}*/
	
	//Use case (11)
	@Test
	public void blockWindowsTest() {
		Room room = new Room();
		room.setWindowState("blocked");
		assertEquals("blocked", room.getWindowState());
	}
	
	//Use case (10)
	@Test
	public void outsideTemperatureTest() {
		Room room = new Room();
		room.setTemperature(0);
		assertEquals(0, room.getTemperature(), 0);
	}
	
	//Use case (5)
	@Test
	public void startSimulationTest() {
		SimulationMasterController.setIsSimulationRunning(true);
		assertEquals(true, SimulationMasterController.getIsSimulationRunning());
	}
		
	//Use case (6)
	@Test
	public void stopSimulationTest() {
		SimulationMasterController.setIsSimulationRunning(false);
		assertEquals(false, SimulationMasterController.getIsSimulationRunning());
	}	
		
	
}
