package soen343.backend;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class SimulationMasterController{
	//Static to be accessed anywhere
	private static boolean isSimulationRunning;
	private static LocalDateTime simulationDateTime;
	private static SecurityModuleModel securityModel;
	private static HeatingModuleModel heatingModel;
	private static CoreModuleModel coreModel;
	private static LayoutModuleModel layoutModel;
	
	SimulationMasterController() {
		setIsSimulationRunning(true);
		
		securityModel = new SecurityModuleModel();
		heatingModel = new HeatingModuleModel();
		coreModel = new CoreModuleModel();
		layoutModel = new LayoutModuleModel();
		
		run();
	}
	
	public static void run() {
		setSimulationDateTime(LocalDateTime.now());
		while (getIsSimulationRunning()) 
		{
			updateCoreModule();
			updateHeatingModule();
			updateSecurityModule();
			updateLayoutModule();
		}
	}
   /**
    * 
    * @return State of simulator
    */
	public static boolean getIsSimulationRunning() {
		return isSimulationRunning;
	}
    /**
     * Turns the simulator on/off depending on the requirement
     * @param isSimulationRunning
     */
	public static void setIsSimulationRunning(boolean isSimulationRunning) {
		SimulationMasterController.isSimulationRunning = isSimulationRunning;
	}
    /**
     * 
     * @return Specific Security model
     */
	public static SecurityModuleModel getSecurityModel() {
		return securityModel;
	}
    /**
     * 
     * @return heating model
     */
	public static HeatingModuleModel getHeatingModel() {
		return heatingModel;
	}
	/**
	 * 
	 * @return core model 
	 */
	public static CoreModuleModel getCoreModel() {
		return coreModel;
	}
    /**
     * 
     * @return layout model
     */
	public static LayoutModuleModel getLayoutModel() {
		return layoutModel;
	}
    /**
     * 
     * @return Currently set date and time in the simulator
     */
	public static LocalDateTime getSimulationDateTime() {
		return CoreModuleModel.getSimulationDateTime();
	}
	/**
	 * 
	 * @return Current date and time seperately
	 */
	public static String getSimulationDateTimeString() 
	{
		return CoreModuleModel.getDate() + " " + CoreModuleModel.getTime();
	}
	/**
	 * <p> Displays date and time depending on whether user has set a date and time. </p>
	 * @param simulationDateTime
	 */
	public static void setSimulationDateTime(LocalDateTime simulationDateTime) {
		if (simulationDateTime != null) {
			CoreModuleModel.setSimulationDateTime(simulationDateTime);
		} else {
			CoreModuleModel.setSimulationDateTime(LocalDateTime.now());
		}
		System.out.println("Time is "+getSimulationDateTimeString());
	}
	/**
	 * Allows user to update date and time
	 */
	public static void updateSimulationDateTime() {
		if (CoreModuleModel.getSimulationDateTime() != null) {
			CoreModuleModel.setSimulationDateTime(CoreModuleModel.getSimulationDateTime().plusSeconds(1));
			System.out.println("Time is "+getSimulationDateTimeString());
		}
	}
	/**
	 * Allows update of security, depending on who is home
	 */
	public static void updateSecurityModule() {
		//Call SecurityModuleModel or the controller?
		while (SecurityModuleModel.isAwayMode())
		{
			//Check for intruders using sensors and motion detectors
		}
	}
	
	public static void updateHeatingModule() {
		//Call HeatingModuleModel or the controller?
	}
	
	public static void updateCoreModule() {
		//Call CoreModuleModel or the controller?
	}
	
	public static void updateLayoutModule() {
		//Call LayoutModuleModel or the controller?
	}
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param lightOn
	 * @return
	 */
	public static boolean toggleLightInRoom(int xPos, int yPos, boolean lightOn) 
	{
		return false;
	}
	/**
	 * 
	 * @return faulse by default
	 */
	public static boolean turnOnAllLights() {
		return false;
	}
	/**
	 * 
	 * @return faulse by default
	 */
	public static boolean turnOffAllLights() {
		return false;
	}
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param windowOpen
	 * @return false by default
	 */
	public static boolean toggleWindowInRoom(int xPos, int yPos, boolean windowOpen) 
	{
		return false;
	}
	/**
	 * 
	 * @return false initially by default
	 */
	public static boolean openAllWindows() {
		return false;
	}
	/**
	 * 
	 * @return False initially by default
	 */
	public static boolean closeAllWindows() {
		return false;
	}
	/**
	 * 
	 * @return False initially by default
	 */
	public static boolean toggleDoorInRoom(int xPos, int yPos, boolean doorOpen) {
		//Use for door, backdoor, garage
		return false;
	}
	/**
	 * Open all doors at once
	 * @return False initially by default
	 */
	public static boolean openAllDoors() {
		//Use for door, backdoor, garage
		return false;
	}
	/**
	 * Close all doors at once
	 * @return False initially by default
	 */
	public static boolean closeAllDoors() {
		//Use for door, backdoor, garage
		return false;
	}
}
