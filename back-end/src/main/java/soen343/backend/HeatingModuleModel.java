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
	private static int firstSummerMonth;
	private static int lastSummerMonth;

	public HeatingModuleModel(){
	}

	public HeatingModuleModel(String zone, ArrayList<String> locations, ArrayList<Integer> period, boolean HVAC, ArrayList<Double> temperature, int firstSummerMonth, int lastSummerMonth) {
		this.zone = zone;
		Locations = locations;
		this.periods = period;
		this.HVAC = HVAC;
		this.temperatures = temperature;
		this.firstSummerMonth = firstSummerMonth;
		this.lastSummerMonth = lastSummerMonth;
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
	
	public int getFirstSummerMonth() {
		return firstSummerMonth;
	}
	
	public void setFirstSummerMonth(int firstSummerMonthNew) {
		firstSummerMonth = firstSummerMonthNew;
	}
	
	public int getLastSummerMonth() {
		return lastSummerMonth;
	}
	
	public void setLastSummerMonth(int lastSummerMonthNew) {
		lastSummerMonth = lastSummerMonthNew;
	}

	@Override
	public String toString() {
		return "HeatingModuleModel{" +
				"zone='" + zone + '\'' +
				", Locations=" + Locations +
				", period=" + periods +
				", HVAC=" + HVAC +
				", temperature=" + temperatures +
				", firstSummerMonth=" + firstSummerMonth +
				", lastSummerMonth=" + lastSummerMonth +
				'}';
	}

}
