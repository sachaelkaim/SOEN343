package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseStatus( HttpStatus.OK )
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    @ResponseStatus( HttpStatus.OK )
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseStatus( HttpStatus.OK )
    public void addUser(@RequestHeader(value = "name") String name){
       userService.addUser(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void editUser(@RequestBody User user, @PathVariable String id) {
        userService.editUser(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void deleteUser(@PathVariable Long id) {
         userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/changeLocation/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void changeUserLocation(@PathVariable Long id,
                                   @RequestBody ObjectNode objectNode){
        userService.changeUserLocation(id, objectNode.get("location").asText());
    }
}
