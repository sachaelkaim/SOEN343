package soen343.backend.room;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    private String name;
    private String windowState;
    private String doorState;
	private boolean lightOn = true;
    private double temperature;
    
    

    public Room() {
		super();
	}

	public Room(int id, String name, String windowState, String doorState, boolean lightOn, double temperature) {
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

	public String getWindowState() {
		return windowState;
	}

	public void setWindowState(String windowState) {
		this.windowState = windowState;
	}

	public String getDoorState() {
		return doorState;
	}

	public void setDoorState(String doorState) {
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
