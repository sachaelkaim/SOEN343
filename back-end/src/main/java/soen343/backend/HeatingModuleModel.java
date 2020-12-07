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

	/**
	 * Instantiates a new Heating module model.
	 */
	public HeatingModuleModel(){
	}

	/**
	 * Instantiates a new Heating module model.
	 *
	 * @param zone        the zone
	 * @param locations   the locations
	 * @param period      the period
	 * @param HVAC        the hvac
	 * @param temperature the temperature
	 */
	public HeatingModuleModel(String zone, ArrayList<String> locations, ArrayList<Integer> period, String HVAC, ArrayList<Double> temperature) {
		this.zone = zone;
		Locations = locations;
		this.periods = period;
		this.HVAC = HVAC;
		this.temperatures = temperature;
	}

	/**
	 * Gets zone.
	 *
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * Sets zone.
	 *
	 * @param zone the zone
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * Gets locations.
	 *
	 * @return the locations
	 */
	public ArrayList<String> getLocations() {
		return Locations;
	}

	/**
	 * Sets locations.
	 *
	 * @param locations the locations
	 */
	public void setLocations(ArrayList<String> locations) {
		Locations = locations;
	}

	/**
	 * Gets periods.
	 *
	 * @return the periods
	 */
	public ArrayList<Integer> getPeriods() {
		return periods;
	}

	/**
	 * Sets periods.
	 *
	 * @param periods the periods
	 */
	public void setPeriods(ArrayList<Integer> periods) {
		this.periods = periods;
	}

	/**
	 * Gets temperatures.
	 *
	 * @return the temperatures
	 */
	public ArrayList<Double> getTemperatures() {
		return temperatures;
	}

	/**
	 * Sets temperatures.
	 *
	 * @param temperatures the temperatures
	 */
	public void setTemperatures(ArrayList<Double> temperatures) {
		this.temperatures = temperatures;
	}

	/**
	 * Is hvac string.
	 *
	 * @return the string
	 */
	public String isHVAC() {
		return HVAC;
	}

	/**
	 * Sets hvac.
	 *
	 * @param HVAC the hvac
	 */
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
