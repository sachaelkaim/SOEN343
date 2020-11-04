package soen343.backend;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import soen343.backend.modules.SimulationMasterService;
import soen343.backend.room.Room;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SimulationContextTest {
	
	
	//Use case (2) and (7)
	@Test
	public void setDateTimeTest() {
		LocalDateTime simDateTime = LocalDateTime.of(2017, 2, 13, 15, 56);
		SimulationMasterService.setSimulationDateTime(simDateTime);
		assertEquals(simDateTime, SimulationMasterService.getSimulationDateTime());
	}
	
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
		SimulationMasterService.setIsSimulationRunning(true);
		assertEquals(true, SimulationMasterService.getIsSimulationRunning());
	}
		
	//Use case (6)
	@Test
	public void stopSimulationTest() {
		SimulationMasterService.setIsSimulationRunning(false);
		assertEquals(false, SimulationMasterService.getIsSimulationRunning());
	}	
		
	
}
