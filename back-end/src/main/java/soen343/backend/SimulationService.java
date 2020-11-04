package soen343.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import soen343.backend.room.RoomService;
import soen343.backend.user.UserService;

@Service
public class SimulationService {

    @Autowired
    private UserService users;

    @Autowired
    private RoomService rooms;

    @Autowired
    private ConsoleService notifications;

    private SecurityModuleModel securityModel;

    /* SECURITY FEATURES*/

    public void setAwayMode(boolean awayMode){
        if(awayMode){
            if(users.allUsersOutside()){
                rooms.closeDoorsWindows();
                notifications.saveNotification(new Console("13:00","SHP","Away mode is set."));
                securityModel.setAwayMode(true);
            }
            else{
                notifications.saveNotification(new Console("13:00","SHP","Away mode cannot be set. All users are not outside"));
            }
        }
        else{
            securityModel.setAwayMode(false);
        }
    }


}
