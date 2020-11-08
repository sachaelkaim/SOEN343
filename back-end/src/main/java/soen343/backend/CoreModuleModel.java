package soen343.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class CoreModuleModel {

	//Static to be accessed anywhere
	public static LocalDateTime simulationDateTime;
	private static boolean AutoMode;
	public static String dateTime;
	public static int timeSpeed = 1;

	public CoreModuleModel() 
	{
	}

	public static LocalDateTime getSimulationDateTime() 
	{
		return simulationDateTime;
	}

	public static void setSimulationDateTime(LocalDateTime simulationDateTime) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateTime = simulationDateTime.format(formatter);
	}

	public static boolean isAutoMode() {
		return AutoMode;
	}

	public static void setAutoMode(boolean autoMode) {
		AutoMode = autoMode;
	}

	public static int getTimeSpeed() {
		return timeSpeed;
	}

	public static void setTimeSpeed(int timeSpeed) {
		CoreModuleModel.timeSpeed = timeSpeed;
	}
}
