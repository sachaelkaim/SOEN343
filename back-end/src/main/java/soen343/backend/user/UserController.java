package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soen343.backend.house.House;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/adduser")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public Iterable<User> list() {
        return userService.list();
    }

}
