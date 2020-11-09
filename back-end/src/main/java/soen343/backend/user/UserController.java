package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * The type User controller.
 */
@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseStatus( HttpStatus.OK )
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get user user.
     *
     * @param id the id
     * @return the user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    @ResponseStatus( HttpStatus.OK )
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    /**
     * Add user.
     *
     * @param name the name
     */
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseStatus( HttpStatus.OK )
    public void addUser(@RequestHeader(value = "name") String name){
       userService.addUser(name);
    }

    /**
     * Login user.
     *
     * @param id the id
     * @return the user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/login/{id}")
    @ResponseStatus( HttpStatus.OK )
    public User login(@PathVariable Long id){
        return userService.login(id);
    }

    /**
     * Edit user.
     *
     * @param user the user
     * @param id   the id
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void editUser(@RequestBody User user, @PathVariable String id) {
        userService.editUser(id, user);
    }

    /**
     * Delete user.
     *
     * @param id the id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void deleteUser(@PathVariable Long id) {
         userService.deleteUser(id);
    }

    /**
     * Change user location.
     *
     * @param id         the id
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/users/changeLocation/{id}")
    @ResponseStatus( HttpStatus.OK )
    public void changeUserLocation(@PathVariable Long id,
                                   @RequestBody ObjectNode objectNode){
        userService.changeUserLocation(id, objectNode.get("location").asText());
    }
}
