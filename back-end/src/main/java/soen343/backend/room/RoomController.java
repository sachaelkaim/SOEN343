package soen343.backend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
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

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{id}")
    public Optional<Room> getRoom(@PathVariable String id){
        return roomService.getRoom(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/{id}")
    public void editRoom(@RequestBody Room room, @PathVariable String id) {
        roomService.editRoom(id, room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{id}")
    public void deleteRoom(@PathVariable String id) {
         roomService.deleteRoom(id);
    }
}
