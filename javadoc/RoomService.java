package soen343.backend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    /**
     * 
     * @return Iterable list of all rooms
     */
    public Iterable<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    /**
     * 
     * @param id
     * @return room information
     */
    public Optional<Room> getRoom(String id){
        return roomRepository.findById(id);
    }
    /**
     * 
     * @param room
     */
    public void addRoom(Room room){
        roomRepository.save(room);
    }
    /**
     * 
     * @param id
     * @param room
     */
    public void editRoom(String id, Room room){
        roomRepository.save(room);
    }
    /**
     * Deletes room by taking id
     * @param id
     */
    public void deleteRoom(String id){
        roomRepository.deleteById(id);
    }
    /**
     * saving list of rooms to repository
     * @param rooms
     */
    public void save(List<Room> rooms){
        roomRepository.saveAll(rooms);
    }

}
