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
    /**
     * 
     * @return name
     */
	public String getName() {
		return name;
	}
	/**
     * 
     * Setter for name
     */ 
	public void setName(String name) {
		this.name = name;
	}
	/**
     * 
     * @return State of Window
     */
	public String getWindowState() {
		return windowState;
	}

	public void setWindowState(String windowState) {
		this.windowState = windowState;
	}
	/**
     * 
     * @return State of Door
     */
	public String getDoorState() {
		return doorState;
	}
	/**
     * 
     * Set State of Door
     */
	public void setDoorState(String doorState) {
		this.doorState = doorState;
	}
	/**
     * 
     * @return boolean setter to check if light is on/off
     */
	public boolean isLightOn() {
		return lightOn;
	}
	/**
     * 
     * Control light on/off
     */
	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}
	/**
     * 
     * @return Temperature
     */
	public double getTemperature() {
		return temperature;
	}
	/**
     * 
     * Setter for Temperature
     */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

    
}
