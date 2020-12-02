package soen343.backend;

import java.util.ArrayList;

/**
 * The type Heating module model.
 */
public class HeatingModuleModel {

	private String zone;
	private ArrayList<String> Locations;

	public HeatingModuleModel(){

	}

	public HeatingModuleModel(String zone, ArrayList<String> locations) {
		this.zone = zone;
		Locations = locations;
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

	@Override
	public String toString() {
		return "HeatingModuleModel{" +
				"zone='" + zone + '\'' +
				", Locations=" + Locations +
				'}';
	}

}
