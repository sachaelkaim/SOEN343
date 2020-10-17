package soen343.backend.room;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	enum WindowState {
		OPENED, CLOSED, BLOCKED, TRANSITIONING
	}

	enum DoorState {
		LOCKED, UNLOCKED, ALERT
	}

	enum Color {
		YELLOW, GREEN, RED
	}

	@Id
	private String name;
	private WindowState windowState;
	private DoorState doorState;
	private boolean lightOn = true;
	private double temperature;
	private Color color;

	public Room() {
		super();
	}

	public Room(int id, String name, WindowState windowState, DoorState doorState, boolean lightOn,
			double temperature) {
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

	public Color getDoorColor(Room room) {
		color = Color.RED;
		switch (room.getDoorState()) {
		case UNLOCKED:
			color = Color.GREEN;
			break;
		case LOCKED:
			color = Color.RED;
			break;
		case ALERT:
			color = Color.YELLOW;
			break;
		default:
			break;
		}
		return color;
	}

	public Color getWindowColor(Room room) {
		color = Color.RED;
		switch (room.getWindowState()) {
		case OPENED:
			color = Color.GREEN;
			break;
		case BLOCKED:
			color = Color.YELLOW;
			break;
		case CLOSED:
			color = Color.RED;
			break;
		default:
			break;
		}
		return color;
	}

}
