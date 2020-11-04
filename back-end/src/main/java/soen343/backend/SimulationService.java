package soen343.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soen343.backend.room.RoomService;
import soen343.backend.user.UserService;

@Service
public class SimulationService {

    @Autowired
    private UserService users;

    @Autowired
    private RoomService rooms;

    private SecurityModuleModel securityModel;

    /* SECURITY FEATURES*/

    public void setAwayMode(boolean awayMode){
        if(awayMode){
            if(users.allUsersOutside()){
                System.out.println("im IN");
                rooms.closeDoorsWindows();
                securityModel.setAwayMode(true);
            }
        }
        else{
            securityModel.setAwayMode(false);
        }
    }


}
