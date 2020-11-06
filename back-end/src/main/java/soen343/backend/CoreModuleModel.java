package soen343.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class CoreModuleModel {

	//Static to be accessed anywhere
	private static LocalDateTime simulationDateTime;
	private static String date;
	private static String time;

	public CoreModuleModel() 
	{
		CoreModuleModel.simulationDateTime = LocalDateTime.now();
	}

	public static LocalDateTime getSimulationDateTime() 
	{
		return simulationDateTime;
	}

	public static void setSimulationDateTime(LocalDateTime simulationDateTime) 
	{
		CoreModuleModel.simulationDateTime = simulationDateTime;
		setDate(simulationDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
		setTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME));
	}

	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		CoreModuleModel.date = date;
	}

	public static String getTime() {
		return time;
	}

	public static void setTime(String time) {
		CoreModuleModel.time = time;
	}
}
