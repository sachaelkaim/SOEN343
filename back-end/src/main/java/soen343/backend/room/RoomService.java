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


/**
 * The type Room service.
 */
@Service
public class RoomService {

    @Autowired
    private StateService state;
    private List<Room> empty = new ArrayList<Room>();

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ConsoleService notifications;

    /**
     * Get all rooms iterable.
     *
     * @return the iterable
     */
    public Iterable<Room> getAllRooms(){
        if(state.getCurrentState()) {
            return roomRepository.findAll();
        }
        else
            return (Iterable<Room>) empty;
    }

    /**
     * Get room room.
     *
     * @param id the id
     * @return the room
     */
    public Room getRoom(String id){
        return roomRepository.findById(id).orElse(null);
    }

    /**
     * Add room.
     *
     * @param room the room
     */
    public void addRoom(Room room){
        roomRepository.save(room);
    }

    /**
     * Edit room.
     *
     * @param id   the id
     * @param room the room
     */
    public void editRoom(String id, Room room){
        roomRepository.save(room);
    }

    /**
     * Delete room.
     *
     * @param id the id
     */
    public void deleteRoom(String id){
        roomRepository.deleteById(id);
    }

    /**
     * Save.
     *
     * @param rooms the rooms
     */
    public void save(List<Room> rooms){
        roomRepository.saveAll(rooms);
    }

    /**
     * Block window boolean.
     *
     * @param location    the location
     * @param windowState the window state
     * @return the boolean
     */
    public boolean blockWindow(String location, String windowState){
        Room blockedRoom = roomRepository.findById(location).orElse(null);
        blockedRoom.setWindowState(windowState);
        roomRepository.save(blockedRoom);
        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHS","Blocked window location: " + blockedRoom.getName() + "."));
        return true;
    }

    /**
     * Set outdoor temperature boolean.
     *
     * @param outdoorTemperature the outdoor temperature
     * @return the boolean
     */
    public boolean setOutdoorTemperature(double outdoorTemperature){
        if(state.getCurrentState()){
            Room outdoorTemp = roomRepository.findById("Outside").orElse(null);
            outdoorTemp.setTemperature(outdoorTemperature);
            roomRepository.save(outdoorTemp);
            notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHS","Set outdoor temperature to: " + outdoorTemp.getTemperature() + "C."));
            return true;
        }
        else
            return false;
    }

    /**
     * Close doors windows.
     */
    public void closeDoorsWindows(){
        Iterable<Room> rooms = roomRepository.findAll();
        Iterator<Room> iter = rooms.iterator();
        while(iter.hasNext()){
            Room room = iter.next();
            if(!"Outside".equals(room.getName()) && !"Backyard".equals(room.getName())){
                room.setWindowState("CLOSED");
                room.setDoorState("LOCKED");
            }
        }
        roomRepository.saveAll(rooms);
    }

    /**
     * On off light.
     *
     * @param location the location
     * @param lightOn  the light on
     */
    public void onOffLight( String location, boolean lightOn ){
        Room room =  roomRepository.findById(location).orElse(null);
        room.setLightOn(lightOn);
        roomRepository.save(room);
    }

    /**
     * Unlock lock door.
     *
     * @param location the location
     * @param doorLock the door lock
     */
    public void unlockLockDoor( String location, String doorLock ){
        Room room =  roomRepository.findById(location).orElse(null);
        room.setDoorState(doorLock);
        roomRepository.save(room);
    }

    /**
     * Open close window boolean.
     *
     * @param location   the location
     * @param windowOpen the window open
     * @return the boolean
     */
    public boolean openCloseWindow( String location, String windowOpen ){
        Room room =  roomRepository.findById(location).orElse(null);
        if(room.getWindowState().equals("BLOCKED")){
            notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",room.getName()+ ". Window is blocked!"));
            return false;
        }
        else
            room.setWindowState(windowOpen);
            roomRepository.save(room);
            return true;
    }

}
