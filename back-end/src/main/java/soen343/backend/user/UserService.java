package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private User parent = new User("1", "john", "Kitchen", "3");

    public void presetUsers(){
        userRepository.save(parent);

    }



    public void addUser(User user){
        userRepository.save(user);
    }

    public Iterable<User> list(){
        return userRepository.findAll();
    }

}
