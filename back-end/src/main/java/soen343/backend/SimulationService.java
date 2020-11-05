package soen343.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import soen343.backend.room.RoomService;
import soen343.backend.state.StateService;
import soen343.backend.user.UserService;

@EnableScheduling
@Service
public class SimulationService {

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

    /* SECURITY FEATURES*/

    public void setAwayMode(boolean awayMode){
        intruderPresent = false;
        if(state.getCurrentState()){
            if(awayMode){
                if(users.allUsersOutside()){
                    rooms.closeDoorsWindows();
                    notifications.saveNotification(new Console("13:00","SHP","Away mode is set."));
                    securityModel.setAwayMode(true);
                }
                else{
                    notifications.saveNotification(new Console("13:00","SHP","Away mode cannot be set. All users are not outside."));
                }
            }
            else{
                securityModel.setAwayMode(false);
            }
        }
    }

    @Scheduled(fixedRate=500)
    public void checkIntruders() {
        if (state.getCurrentState()) {
            if (securityModel.isAwayMode() && !intruderPresent) {
                if (!users.allUsersOutside()) {
                    notifications.saveNotification(new Console("13:00", "SHP", "There are intruders..."));
                    intruderPresent = true;
                }
            }
        }
    }

}
