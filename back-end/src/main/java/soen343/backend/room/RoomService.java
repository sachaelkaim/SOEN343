package soen343.backend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
        return true;
    }

    public boolean setOutdoorTemperature(double outdoorTemperature){
        if(state.getCurrentState()){
            System.out.println(outdoorTemperature);
            Room outdoorTemp = roomRepository.findById("Outside").orElse(null);
            outdoorTemp.setTemperature(outdoorTemperature);
            roomRepository.save(outdoorTemp);
            return true;
        }
        else
            return false;
    }

}
