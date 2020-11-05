package soen343.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import soen343.backend.room.RoomService;
import soen343.backend.state.StateService;
import soen343.backend.user.UserService;
import java.time.LocalDateTime;

@EnableScheduling
@Service
public class SimulationService {

    private static LocalDateTime simulationDateTime;
    private static CoreModuleModel coreModel;
    private static SecurityModuleModel securityModel;
    boolean intruderPresent = false;

    @Autowired
    private  UserService users;

    @Autowired
    private  ConsoleService notifications;

    @Autowired
    private RoomService rooms;

    @Autowired
    private StateService state;

    /* DATE AND TIME */

    @Bean
    public void startDateAndTime(){
        coreModel.setSimulationDateTime(LocalDateTime.now());
    }

    @Scheduled(fixedRate=500)
    public void dateAndTime() {
        CoreModuleModel.setSimulationDateTime(CoreModuleModel.getSimulationDateTime().plusSeconds(1));
    }

    public LocalDateTime getDateAndTime(){
        return  CoreModuleModel.getSimulationDateTime();
    }

    /* SHC FEATURES*/

    public void setLight(String userLocation, String privilege, String location, boolean lightOn){
        if(state.getCurrentState()){
            switch (privilege) {
                case "0":
                    rooms.onOffLight(location,lightOn);
                    if(lightOn == true)
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is on."));
                    else
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is off."));
                    break;

                case "1":
                    if(userLocation.equals(location)){
                        rooms.onOffLight(location,lightOn);
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is off."));
                    }
                    else
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + "Guest/Child does not have permission."));
                    break;

                case "3":
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC","Stranger does not have permission."));
                    break;
            }

        }


    }


    /* SHP FEATURES*/

    public void setAwayMode(boolean awayMode){
        intruderPresent = false;
        if(state.getCurrentState())
            if(awayMode){
                if(users.allUsersOutside()){
                    rooms.closeDoorsWindows();
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHP","Away mode is set."));
                    securityModel.setAwayMode(true);
                }
                else{
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHP","Away mode cannot be set. All users are not outside."));
                }
            }
            else{
                securityModel.setAwayMode(false);
            }
    }


    @Scheduled(fixedRate=500)
    public void checkIntruders() {
        if (state.getCurrentState()) {
            if (securityModel.isAwayMode() && !intruderPresent) {
                if (!users.allUsersOutside()) {
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(), "SHP", "There are intruders..."));
                    intruderPresent = true;
                }
            }
        }
    }

}
