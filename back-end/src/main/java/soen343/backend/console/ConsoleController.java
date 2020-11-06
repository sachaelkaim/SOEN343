package soen343.backend.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import soen343.backend.room.Room;
import soen343.backend.room.RoomService;

@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;

    @RequestMapping(method = RequestMethod.GET, value = "/console")
    @ResponseStatus( HttpStatus.OK )
    public Iterable<Console> getAllRooms() {
        return consoleService.getAllNotifications();
    }

}
