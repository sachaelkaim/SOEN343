package soen343.backend.room;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The type Room.
 */
@Entity
public class Room {

    @Id
    private String name;
    private String windowState;
    private String doorState;
	private boolean lightOn = true;
    private double temperature;


    /**
     * Instantiates a new Room.
     */
    public Room() {
		super();
	}

    /**
     * Instantiates a new Room.
     *
     * @param id          the id
     * @param name        the name
     * @param windowState the window state
     * @param doorState   the door state
     * @param lightOn     the light on
     * @param temperature the temperature
     */
    public Room(int id, String name, String windowState, String doorState, boolean lightOn, double temperature) {
		super();
		this.name = name;
		this.windowState = windowState;
		this.doorState = doorState;
		this.lightOn = lightOn;
		this.temperature = temperature;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets window state.
     *
     * @return the window state
     */
    public String getWindowState() {
		return windowState;
	}

    /**
     * Sets window state.
     *
     * @param windowState the window state
     */
    public void setWindowState(String windowState) {
		this.windowState = windowState;
	}

    /**
     * Gets door state.
     *
     * @return the door state
     */
    public String getDoorState() {
		return doorState;
	}

    /**
     * Sets door state.
     *
     * @param doorState the door state
     */
    public void setDoorState(String doorState) {
		this.doorState = doorState;
	}

    /**
     * Is light on boolean.
     *
     * @return the boolean
     */
    public boolean isLightOn() {
		return lightOn;
	}

    /**
     * Sets light on.
     *
     * @param lightOn the light on
     */
    public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public double getTemperature() {
		return temperature;
	}

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

    
}
