package soen343.backend.room;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * The type Room controller.
 */
@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Instantiates a new Room controller.
     *
     * @param roomService the room service
     */
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Gets all rooms.
     *
     * @return the all rooms
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    @ResponseStatus( HttpStatus.OK )
    public Iterable<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * Gets room.
     *
     * @param name the name
     * @return the room
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{name}")
    @ResponseStatus( HttpStatus.OK )
    public Room getRoom(@RequestHeader(value = "name") String name){
        return roomService.getRoom(name);
    }

    /**
     * Add room.
     *
     * @param room the room
     */
    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    @ResponseStatus( HttpStatus.OK )
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    /**
     * Edit room.
     *
     * @param room the room
     * @param name the name
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/{name}")
    @ResponseStatus( HttpStatus.OK )
    public void editRoom(@RequestBody Room room, @PathVariable String name) {
        roomService.editRoom(name, room);
    }

    /**
     * Delete room.
     *
     * @param name the name
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{name}")
    @ResponseStatus( HttpStatus.OK )
    public void deleteRoom(@PathVariable String name) {
        roomService.deleteRoom(name);
    }

    /**
     * Block window.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/blockLocation")
    @ResponseStatus( HttpStatus.OK )
    public void blockWindow(@RequestBody ObjectNode objectNode){
        roomService.blockWindow(objectNode.get("location").asText(),objectNode.get("windowState").asText());
    }

    /**
     * Set outdoor temperature.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/outdoorTemperature")
    @ResponseStatus( HttpStatus.OK )
    public void setOutdoorTemperature(@RequestBody ObjectNode objectNode){
        roomService.setOutdoorTemperature(objectNode.get("temperature").asDouble());
    }

}