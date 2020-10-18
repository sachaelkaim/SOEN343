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

    public Iterable<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Optional<Room> getRoom(String id){
        return roomRepository.findById(id);
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

}