package soen343.backend.modules;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import soen343.backend.room.RoomService;
import soen343.backend.user.UserService;

@Service
@EnableScheduling
public class SimulationMasterService {
	//Static to be accessed anywhere
	private static boolean isSimulationRunning;
	private static LocalDateTime simulationDateTime;
	private static SecurityModuleModel securityModel;
	private static HeatingModuleModel heatingModel;
	private static CoreModuleModel coreModel;
	private static LayoutModuleModel layoutModel;
	private UserService users;
	private RoomService rooms;

	public SimulationMasterService() {
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

	public static boolean getIsSimulationRunning() {
		return isSimulationRunning;
	}

	public static void setIsSimulationRunning(boolean isSimulationRunning) {
		SimulationMasterService.isSimulationRunning = isSimulationRunning;
	}

	public static SecurityModuleModel getSecurityModel() {
		return securityModel;
	}

	public static HeatingModuleModel getHeatingModel() {
		return heatingModel;
	}
	
	public static CoreModuleModel getCoreModel() {
		return coreModel;
	}

	public static LayoutModuleModel getLayoutModel() {
		return layoutModel;
	}

	public static LocalDateTime getSimulationDateTime() {
		return CoreModuleModel.getSimulationDateTime();
	}
	
	public static String getSimulationDateTimeString() 
	{
		return CoreModuleModel.getDate() + " " + CoreModuleModel.getTime();
	}
	
	public static void setSimulationDateTime(LocalDateTime simulationDateTime) {
		if (simulationDateTime != null) {
			CoreModuleModel.setSimulationDateTime(simulationDateTime);
		} else {
			CoreModuleModel.setSimulationDateTime(LocalDateTime.now());
		}
		System.out.println("Time is "+getSimulationDateTimeString());
	}
	
	public static void updateSimulationDateTime() {
		if (CoreModuleModel.getSimulationDateTime() != null) {
			CoreModuleModel.setSimulationDateTime(CoreModuleModel.getSimulationDateTime().plusSeconds(1));
			System.out.println("Time is "+getSimulationDateTimeString());
		}
	}

	public void setAwayMode(boolean awayMode){
		if(awayMode == true){
			if(users.allUsersOutside()){
				rooms.closeDoorsWindows();
				SecurityModuleModel.setAwayMode(true);
			}
		}
		else{
			SecurityModuleModel.setAwayMode(false);
		}
	}

	public static void updateSecurityModule() {
		//Call SecurityModuleModel or the controller?
		while (SecurityModuleModel.isAwayMode())
		{
			System.out.println("im in updatesecuritymodule");
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
	
	public static boolean toggleLightInRoom(int xPos, int yPos, boolean lightOn) 
	{
		return false;
	}
	
	public static boolean turnOnAllLights() {
		return false;
	}
	
	public static boolean turnOffAllLights() {
		return false;
	}
	
	public static boolean toggleWindowInRoom(int xPos, int yPos, boolean windowOpen) 
	{
		return false;
	}
	
	public static boolean openAllWindows() {
		return false;
	}
	
	public static boolean closeAllWindows() {
		return false;
	}
	
	public static boolean toggleDoorInRoom(int xPos, int yPos, boolean doorOpen) {
		//Use for door, backdoor, garage
		return false;
	}
	
	public static boolean openAllDoors() {
		//Use for door, backdoor, garage
		return false;
	}
	
	public static boolean closeAllDoors() {
		//Use for door, backdoor, garage
		return false;
	}
}
