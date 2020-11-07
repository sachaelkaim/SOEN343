package soen343.backend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soen343.backend.CoreModuleModel;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import soen343.backend.state.StateService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



@Service
public class RoomService {

    @Autowired
    private StateService state;
    private List<Room> empty = new ArrayList<Room>();

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ConsoleService notifications;

    public Iterable<Room> getAllRooms(){
        if(state.getCurrentState()) {
            return roomRepository.findAll();
        }
        else
            return (Iterable<Room>) empty;
    }

    public Room getRoom(String id){
        return roomRepository.findById(id).orElse(null);
    }

    public void addRoom(Room room){
        roomRepository.save(room);
    }

    public void editRoom(String id, Room room){
        roomRepository.save(room);
    }

    public void deleteRoom(String id){
        roomRepository.deleteById(id);
    }
    
    public void save(List<Room> rooms){
        roomRepository.saveAll(rooms);
    }

    public boolean blockWindow(String location, String windowState){
        Room blockedRoom = roomRepository.findById(location).orElse(null);
        blockedRoom.setWindowState(windowState);
        roomRepository.save(blockedRoom);
        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHS","Blocked window location: " + blockedRoom.getName() + "."));
        return true;
    }

    public boolean setOutdoorTemperature(double outdoorTemperature){
        if(state.getCurrentState()){
            Room outdoorTemp = roomRepository.findById("Outside").orElse(null);
            outdoorTemp.setTemperature(outdoorTemperature);
            roomRepository.save(outdoorTemp);
            notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHS","Set outdoor temperature to: " + outdoorTemp.getTemperature() + "C."));
            return true;
        }
        else
            return false;
    }

    public void closeDoorsWindows(){
        Iterable<Room> rooms = roomRepository.findAll();
        Iterator<Room> iter = rooms.iterator();
        while(iter.hasNext()){
            Room room = iter.next();
            if(!"Outside".equals(room.getName()) && !"Backyard".equals(room.getName())){
                System.out.print(room.getName());
                room.setWindowState("CLOSED");
                room.setDoorState("LOCKED");
            }
        }
        roomRepository.saveAll(rooms);
    }

    public void onOffLight( String location, boolean lightOn ){
        Room room =  roomRepository.findById(location).orElse(null);
        room.setLightOn(lightOn);
        roomRepository.save(room);
    }

    public void unlockLockDoor( String location, String doorLock ){
        Room room =  roomRepository.findById(location).orElse(null);
        room.setDoorState(doorLock);
        roomRepository.save(room);
    }

    public boolean openCloseWindow( String location, String windowOpen ){
        Room room =  roomRepository.findById(location).orElse(null);
        if(room.getWindowState().equals("BLOCKED")){
            notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHC",room.getName()+ ". Window is blocked!"));
            return false;
        }
        else
            room.setWindowState(windowOpen);
            roomRepository.save(room);
            return true;
    }

}
