package soen343.backend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class RoomController {

    @Autowired
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    /**
     * 
     * @return Access to iterable list of all rooms
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public Iterable<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    /**
     * 
     * @param name Name of room as a string, passed from layout file
     * @return Specific room data
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{name}")
    public Optional<Room> getRoom(@PathVariable String name){
        return roomService.getRoom(name);
    }
    /**
     * 
     * @param room 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }
    /**
     * 
     * @param room
     * @param name
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/{name}")
    public void editRoom(@RequestBody Room room, @PathVariable String name) {
        roomService.editRoom(name, room);
    }
    /**
     * 
     * @param name
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{name}")
    public void deleteRoom(@PathVariable String name) {
         roomService.deleteRoom(name);
    }
}
