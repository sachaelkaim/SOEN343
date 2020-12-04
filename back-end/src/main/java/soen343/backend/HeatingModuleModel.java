package soen343.backend;

import java.util.ArrayList;

/**
 * The type Heating module model.
 */
public class HeatingModuleModel {

	private String zone;
	private ArrayList<String> Locations;
	private ArrayList<Integer> periods;
	private boolean HVAC;
	private ArrayList<Double> temperatures;

	public HeatingModuleModel(){
	}

	public HeatingModuleModel(String zone, ArrayList<String> locations, ArrayList<Integer> period, boolean HVAC, ArrayList<Double> temperature) {
		this.zone = zone;
		Locations = locations;
		this.periods = period;
		this.HVAC = HVAC;
		this.temperatures = temperature;
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

	public ArrayList<Integer> getPeriods() {
		return periods;
	}

	public void setPeriods(ArrayList<Integer> periods) {
		this.periods = periods;
	}

	public ArrayList<Double> getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(ArrayList<Double> temperatures) {
		this.temperatures = temperatures;
	}

	public boolean isHVAC() {
		return HVAC;
	}

	public void setHVAC(boolean HVAC) {
		this.HVAC = HVAC;
	}

	@Override
	public String toString() {
		return "HeatingModuleModel{" +
				"zone='" + zone + '\'' +
				", Locations=" + Locations +
				", period=" + periods +
				", HVAC=" + HVAC +
				", temperature=" + temperatures +
				'}';
	}

}
