package soen343.backend;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import soen343.backend.room.Room;
import soen343.backend.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulationServicesTest {

	private User parent = new User("Peter", "Outside", "0");
	private User child = new User("Adam", "Outside", "1");
	private User guest = new User("Elle", "Outside", "2");
	private User stranger = new User("Rick", "Outside", "3");

	@Autowired
	private SimulationService sim;
	
	@Autowired
	private CoreModuleController core;


	// Use Case (1) and (6)
	@Test
	public void setLightTest() {
		Room room = new Room();
		room.setLightOn(false);
		sim.setLight(parent.getLocation(), parent.getPrivilege(), "Kitchen", true);
		assertEquals(true, room.isLightOn());
		room.setLightOn(false);
		sim.setLight(child.getLocation(), child.getPrivilege(), "Kitchen", true);
		assertEquals(false, room.isLightOn());
		room.setLightOn(false);
		sim.setLight(guest.getLocation(), guest.getPrivilege(), "Kitchen", true);
		assertEquals(false, room.isLightOn());
		room.setLightOn(false);
		sim.setLight(stranger.getLocation(), stranger.getPrivilege(), "Kitchen", true);
		assertEquals(false, room.isLightOn());
	}

	// Use Case (1) and (4)
	@Test
	public void setDoorTest() {
		Room room = new Room();
		room.setDoorState("LOCKED");
		sim.setDoor(parent.getPrivilege(), "Kitchen", "UNLOCKED");
		assertEquals("UNLOCKED", room.getDoorState());
		room.setDoorState("LOCKED");
		sim.setDoor(child.getPrivilege(), "Kitchen", "UNLOCKED");
		assertEquals("LOCKED", room.getDoorState());
		room.setDoorState("LOCKED");
		sim.setDoor(guest.getPrivilege(), "Kitchen", "UNLOCKED");
		assertEquals("LOCKED", room.getDoorState());
		room.setDoorState("LOCKED");
		sim.setDoor(stranger.getPrivilege(), "Kitchen", "UNLOCKED");
		assertEquals("LOCKED", room.getDoorState());
	}

	// Use Case (1) and (5)
	@Test
	public void setWindowTest() {
		Room room = new Room();
		room.setWindowState("CLOSE");
		sim.setWindow(parent.getLocation(), parent.getPrivilege(), "Kitchen", "OPEN");
		assertEquals("OPEN", room.getWindowState());
		room.setWindowState("CLOSE");
		sim.setWindow(child.getLocation(), child.getPrivilege(), "Kitchen", "OPEN");
		assertEquals("CLOSE", room.getWindowState());
		room.setWindowState("CLOSE");
		sim.setWindow(guest.getLocation(), guest.getPrivilege(), "Kitchen", "OPEN");
		assertEquals("CLOSE", room.getWindowState());
		room.setWindowState("CLOSE");
		sim.setWindow(stranger.getLocation(), stranger.getPrivilege(), "Kitchen", "OPEN");
		assertEquals("CLOSE", room.getWindowState());
	}

	// Use Case (1) and (8)
	@Test
	public void setAwayModeTest() {
		SecurityModuleModel.setAwayMode(false);
		sim.setAwayMode(true, parent.getPrivilege());
		assertEquals(true, SecurityModuleModel.isAwayMode());
		SecurityModuleModel.setAwayMode(false);
		sim.setAwayMode(true, child.getPrivilege());
		assertEquals(true, SecurityModuleModel.isAwayMode());
		SecurityModuleModel.setAwayMode(false);
		sim.setAwayMode(true, guest.getPrivilege());
		assertEquals(false, SecurityModuleModel.isAwayMode());
		SecurityModuleModel.setAwayMode(false);
		sim.setAwayMode(true, stranger.getPrivilege());
		assertEquals(false, SecurityModuleModel.isAwayMode());
	}
	
	// Use Case (9)
	@Test
	public void checkIntrudersTest() {
		SecurityModuleModel.setAwayMode(true);
		stranger.setLocation("Kitchen");
		sim.checkIntruders();
		assertEquals(true, sim.intruderPresent);
	}
	
	// Use Case (7)
	@Test
	public void autoModeTest() {
		Room room = new Room();
		room.setName("Kitchen");
		room.setLightOn(false);
		parent.setLocation("Kitchen");
		sim.autoMode();
		assertEquals(true, room.isLightOn());
	}
	
	//Use Case (3)
	@Test
	public void changeTimeSpeedTest() {
		CoreModuleModel.setTimeSpeed(10);
		ObjectMapper mapper = new ObjectMapper();
	    ObjectNode objectNode = mapper.createObjectNode();
	    objectNode.put("timeSpeed", 20);
		core.timeSpeed(objectNode);
		assertEquals(20, CoreModuleModel.getTimeSpeed());
	}
}
