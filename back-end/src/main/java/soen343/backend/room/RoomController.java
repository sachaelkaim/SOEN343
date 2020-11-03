package soen343.backend.room;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
    @ResponseStatus( HttpStatus.OK )
    public Iterable<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{name}")
    @ResponseStatus( HttpStatus.OK )
    public Room getRoom(@RequestHeader(value = "name") String name){
        return roomService.getRoom(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    @ResponseStatus( HttpStatus.OK )
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/{name}")
    @ResponseStatus( HttpStatus.OK )
    public void editRoom(@RequestBody Room room, @PathVariable String name) {
        roomService.editRoom(name, room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{name}")
    @ResponseStatus( HttpStatus.OK )
    public void deleteRoom(@PathVariable String name) {
        roomService.deleteRoom(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/blockLocation/")
    @ResponseStatus( HttpStatus.OK )
    public void blockWindow(@RequestBody ObjectNode objectNode){
        roomService.blockWindow(objectNode.get("location").asText(),objectNode.get("windowState").asText());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/outdoorTemperature/")
    @ResponseStatus( HttpStatus.OK )
    public void outdoorTemperature(@RequestBody ObjectNode objectNode){
        roomService.blockWindow(objectNode.get("temperature").asText());
    }

}