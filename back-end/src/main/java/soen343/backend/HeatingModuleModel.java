package soen343.backend;

import java.util.ArrayList;

/**
 * The type Heating module model.
 */
public class HeatingModuleModel {

	private String zone;
	private ArrayList<String> Locations;
	private int period;
	private double temperature;

	public HeatingModuleModel(){
	}

	public HeatingModuleModel(String zone, ArrayList<String> locations, int period, double temperature) {
		this.zone = zone;
		Locations = locations;
		this.period = period;
		this.temperature = temperature;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public ArrayList<String> getLocations() {
		return Locations;
	}

	public void setLocations(ArrayList<String> locations) {
		Locations = locations;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "HeatingModuleModel{" +
				"zone='" + zone + '\'' +
				", Locations=" + Locations +
				", period=" + period +
				", temperature=" + temperature +
				'}';
	}

}
