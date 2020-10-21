package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Constructor for UserController
     * @param userService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Function that return all the Users in the dataBase
     * @return List of users
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Function that retrieve the User using it's Id
     * @param id
     * @return User's Id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    /**
     * Function that add a new User to the List of Users
     * @param name
     */
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addParent(@RequestHeader(value = "name") String name){
        if(name.equals("parent")){
            userService.addParent();
        }
        else if(name.equals("child")){
            userService.addChildren();
        }
        else if(name.equals("guest")){
            userService.addGuest();
        }
        else{
            userService.addStranger();
        }
    }

    /**
     * Function that makes it possible to change the details of the User
     * @param user
     * @param id
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void editUser(@RequestBody User user, @PathVariable String id) {
        userService.editUser(id, user);
    }

    /**
     * Function that removes a User in the Users' list
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Long id) {
         userService.deleteUser(id);
    }
}
