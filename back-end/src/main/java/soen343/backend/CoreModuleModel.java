package soen343.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * The type Core module model.
 */
public class CoreModuleModel {

    /**
     * The constant simulationDateTime.
     */
//Static to be accessed anywhere
	public static LocalDateTime simulationDateTime;
	private static boolean AutoMode;
    /**
     * The constant dateTime.
     */
    public static String dateTime;
    /**
     * The constant timeSpeed.
     */
    public static int timeSpeed = 1;

    /**
     * Instantiates a new Core module model.
     */
    public CoreModuleModel()
	{
	}

    /**
     * Gets simulation date time.
     *
     * @return the simulation date time
     */
    public static LocalDateTime getSimulationDateTime()
	{
		return simulationDateTime;
	}

    /**
     * Sets simulation date time.
     *
     * @param simulationDateTime the simulation date time
     */
    public static void setSimulationDateTime(LocalDateTime simulationDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateTime = simulationDateTime.format(formatter);
	}

    /**
     * Is auto mode boolean.
     *
     * @return the boolean
     */
    public static boolean isAutoMode() {
		return AutoMode;
	}

    /**
     * Sets auto mode.
     *
     * @param autoMode the auto mode
     */
    public static void setAutoMode(boolean autoMode) {
		AutoMode = autoMode;
	}

    /**
     * Gets time speed.
     *
     * @return the time speed
     */
    public static int getTimeSpeed() {
		return timeSpeed;
	}

    /**
     * Sets time speed.
     *
     * @param timeSpeed the time speed
     */
    public static void setTimeSpeed(int timeSpeed) {
		CoreModuleModel.timeSpeed = timeSpeed;
	}
}
