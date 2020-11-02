package soen343.backend.room;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soen343.backend.state.StateService;
import soen343.backend.user.User;

@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class RoomController {

    @Autowired
    private StateService state;

    @JsonSerialize
    public class EmptyJsonResponse { }

    @Autowired
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public Iterable<Room> getAllRooms() {
        if(state.getCurrentState()){ //if simulation is on, send house layout
            return roomService.getAllRooms();
        }
        else
            return (Iterable<Room>) new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{name}")
    public Room getRoom(@RequestHeader(value = "name") String name){
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

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/blockLocation/")
    public void blockWindow(@RequestBody ObjectNode objectNode){
        String location = objectNode.get("location").asText();
        Room tempRoom = roomService.getRoom(location);
        tempRoom.setWindowState(objectNode.get("windowState").asText());
        roomService.editRoom(location, tempRoom);
    }

}
