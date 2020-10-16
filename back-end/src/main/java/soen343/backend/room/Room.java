package soen343.backend.room;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	enum WindowState {OPENED,CLOSED,BLOCKED,TRANSITIONING}
	enum DoorState {LOCKED, UNLOCKED, ALERT}

    @Id
    private String name;
    private WindowState windowState;
    private DoorState doorState;
    private boolean lightOn = true;
    private double temperature;
    
    

    public Room() {
		super();
	}

	public Room(int id, String name, WindowState windowState, DoorState doorState, boolean lightOn, double temperature) {
		super();
		this.name = name;
		this.windowState = windowState;
		this.doorState = doorState;
		this.lightOn = lightOn;
		this.temperature = temperature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WindowState getWindowState() {
		return windowState;
	}

	public void setWindowState(WindowState windowState) {
		this.windowState = windowState;
	}

	public DoorState getDoorState() {
		return doorState;
	}

	public void setDoorState(DoorState doorState) {
		this.doorState = doorState;
	}

	public boolean isLightOn() {
		return lightOn;
	}

	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}
	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

    
}
