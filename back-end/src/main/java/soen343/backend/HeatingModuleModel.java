package soen343.backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Heating module model.
 */
public class HeatingModuleModel {

	private String zone;
	private ArrayList<String> Locations;
	private ArrayList<Integer> periods = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
	private String HVAC;
	private ArrayList<Double> temperatures = new ArrayList<Double>(Arrays.asList(-1000.0, -1000.0, -1000.0));

	public HeatingModuleModel(){
	}

	public HeatingModuleModel(String zone, ArrayList<String> locations, ArrayList<Integer> period, String HVAC, ArrayList<Double> temperature) {
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

	public String isHVAC() {
		return HVAC;
	}

	public void setHVAC(String HVAC) {
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
