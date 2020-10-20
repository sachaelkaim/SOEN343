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

    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public Iterable<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{name}")
    public Optional<Room> getRoom(@PathVariable String name){
        return roomService.getRoom(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/{name}")
    public void editRoom(@RequestBody Room room, @PathVariable String name) {
        roomService.editRoom(name, room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{name}")
    public void deleteRoom(@PathVariable String name) {
         roomService.deleteRoom(name);
    }
}
