package soen343.backend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import soen343.backend.room.Room;
import soen343.backend.room.RoomService;
import soen343.backend.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulationServicesTestDel3 {

	private User parent = new User("Peter", "Outside", "0");

	@Autowired
	private SimulationService sim;
	
	@Autowired
	private ArrayList<String> locations;
	
	@Autowired
	private HeatingModuleModel zone;
	private List<HeatingModuleModel> zones = sim.displayZones();


	// Use Case (1)
	@Test
	public void addZoneTest() {
		locations.add("Kitchen");
		locations.add("Garage");
		zone.setLocations(locations);
		sim.addZone(locations, parent.getPrivilege());;
		assertEquals(zone, zones.get(0));
	}

	// Use Case (2) and (5)
	@Test
	public void setZoneTempTest() {
		zone.setZone("Zone1");
		ArrayList<Double> temperatures = new ArrayList<>(zone.getTemperatures());
        temperatures.set(0, (double)30);
		sim.setZoneTemperature(parent.getPrivilege(), zone.getZone(), 0, 30);
		sim.regulateZoneTemperatures();
		assertEquals(zone.getTemperatures(), zones.get(0).getTemperatures());
	}
	
	// Use Case (3)
	@Test
	public void displayTempTest() {
		Room room = new Room();
		room.setTemperature(30);
		assertEquals(30, room.getTemperature(), 0);
	}
	
	// Use Case (4)
	@Test
	public void windowsTest() {
		Room room = new Room();
		RoomService rs = new RoomService();
		room.setName("Kitchen");
		room.setWindowState("OPEN");
		rs.blockWindow(room.getName(), "OPEN");
		sim.setWindow("Kitchen", parent.getPrivilege(), "Kitchen", "CLOSE");
		assertEquals("OPEN", room.getWindowState());
	}	
	
	// Use Case (6)
	@Test
	public void zoneToOutsideTempTest() {
		zone.setZone("Zone1");
		locations.add("Kitchen");
		locations.add("Garage");
		zone.setLocations(locations);
		ArrayList<Double> temperatures = new ArrayList<>(zone.getTemperatures());
        temperatures.set(0, (double)21);
        sim.setZoneTemperature(parent.getPrivilege(), "Zone1", 0, 30);
        sim.zoneToOutsideTemperature(zone, locations);
		assertEquals(zone.getTemperatures(), zones.get(0).getTemperatures());
	}
	
}
