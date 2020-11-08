package soen343.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import soen343.backend.room.Room;
import soen343.backend.room.RoomRepository;
import soen343.backend.room.RoomService;
import soen343.backend.state.StateService;
import soen343.backend.user.User;
import soen343.backend.user.UserRepository;
import soen343.backend.user.UserService;
import java.time.LocalDateTime;
import java.util.Iterator;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    /* DATE AND TIME */

    @Bean
    public void startDateAndTime(){
        coreModel.setSimulationDateTime(LocalDateTime.now());
    }

    @Scheduled(fixedRate=500)
    public void dateAndTime() {
        if(state.getCurrentState()) {
            CoreModuleModel.setSimulationDateTime(CoreModuleModel.getSimulationDateTime().plusSeconds(1));
        }
        else
            CoreModuleModel.setTime(CoreModuleModel.getTime());
    }

    /* SHC FEATURES*/

    public void setLight(String userLocation, String privilege, String location, boolean lightOn){
        if(state.getCurrentState()){
            if(privilege.equals("0")){
                rooms.onOffLight(location,lightOn);
                if(lightOn == true)
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is on."));
                else
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is off."));
            }
            if(privilege.equals("1") || privilege.equals("2")){
                if(userLocation.equals("Outside")){
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Guest/Child does not have permission."));
                }
                else if(userLocation.equals(location)){
                    rooms.onOffLight(location,lightOn);
                    if(lightOn == true){
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is on."));
                    }
                    else
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is off."));
                }
                else
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Guest/Child does not have permission."));
            }
            if(privilege.equals("3")){
                notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Stranger does not have permission."));
            }
        }
    }

    public void setDoor(String privilege, String location, String doorLock){
        if(state.getCurrentState()){
            if(privilege.equals("0")){
                rooms.unlockLockDoor(location,doorLock);
                if(doorLock.equals("UNLOCKED"))
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " door is unlocked."));
                else
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " door is locked."));
            }
            else
                notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Stranger/Guest/Child does not have permission."));
        }
    }

    public void setWindow(String userLocation, String privilege, String location, String windowOpen){
        if(state.getCurrentState()){
            if(privilege.equals("0")){
                boolean notBlocked = rooms.openCloseWindow(location,windowOpen);
                if(notBlocked){
                    if(windowOpen.equals("OPEN"))
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is on."));
                    else
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is off."));
                }
            }
            if(privilege.equals("1") || privilege.equals("2")){
                if(userLocation.equals("Outside")){
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Guest/Child does not have permission."));
                }
                else if(userLocation.equals(location)){
                    boolean notBlocked = rooms.openCloseWindow(location,windowOpen);
                    if(notBlocked){
                        if(windowOpen.equals("OPEN"))
                            notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is on."));
                        else
                            notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + " light is off."));
                    }
                }
                else
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Guest/Child does not have permission."));
            }
            if(privilege.equals("3")){
                notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",location + ". Stranger does not have permission."));
            }
        }
    }

    public void setAutoMode(boolean autoMode){
        CoreModuleModel.setAutoMode(autoMode);
        if(state.getCurrentState()){
            if(autoMode){
                notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",  "Auto Mode is on."));
                autoMode();
            }

            else
                notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",  "Auto Mode is off."));
        }
    }

    public void autoMode(){
        if(CoreModuleModel.isAutoMode()) {
            Iterable<User> users = userRepository.findAll();
            Iterator<User> iter = users.iterator();
            while (iter.hasNext()) {
                User user = iter.next();
                Iterable<Room> rooms = roomRepository.findAll();
                Iterator<Room> iter1 = rooms.iterator();
                while(iter1.hasNext()){
                    Room room = iter1.next();
                    if(user.getLocation().equals(room.getName())){
                        room.setLightOn(true);
                        roomRepository.save(room);
                    }
                }
            }
            Iterable<Room> rooms = roomRepository.findAll();
            Iterator<Room> iter2 = rooms.iterator();
            while(iter2.hasNext()){
                int i = 0;
                Room room = iter2.next();
                Iterator<User> iter3 = users.iterator();
                while (iter3.hasNext()) {
                    User user = iter3.next();
                    if (user.getLocation().equals(room.getName())){
                        i++;
                    }
                }
                if(i == 0){
                    room.setLightOn(false);
                    roomRepository.save(room);
                }
            }
        }
    }

    /* SHP FEATURES*/

    public void setAwayMode(boolean awayMode, String userPrivilege){
        intruderPresent = false;
        if(state.getCurrentState())
            if(awayMode){
                if(users.allUsersOutside()){
                    if(userPrivilege.equals("2") || userPrivilege.equals("3")){
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHP","Away mode cannot be set by Stranger/Guest"));
                    }
                    else{
                        rooms.closeDoorsWindows();
                        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHP","Away mode is on."));
                        securityModel.setAwayMode(true);
                    }
                }
                else{
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHP","Away mode cannot be set. All users are not outside."));
                }
            }
            else{
                securityModel.setAwayMode(false);
                notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHP","Away mode is off."));
            }
    }


    @Scheduled(fixedRate=500)
    public void checkIntruders() {
        if (state.getCurrentState()) {
            if (securityModel.isAwayMode() && !intruderPresent) {
                if (!users.allUsersOutside()) {
                    notifications.saveNotification(new Console(CoreModuleModel.getTime(), "SHC", "There are intruders..."));
                    intruderPresent = true;
                }
            }
        }
    }

}
