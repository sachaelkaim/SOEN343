package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

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


    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void editUser(@RequestBody User user, @PathVariable String id) {
        userService.editUser(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Long id) {
         userService.deleteUser(id);
    }
}
