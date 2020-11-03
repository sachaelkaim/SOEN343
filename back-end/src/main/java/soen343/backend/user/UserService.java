package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void addBaseUsers(){
        userRepository.save(new User( "parent", "Outside", "0"));
        userRepository.save(new User( "child", "Outside", "1"));
        userRepository.save(new User( "guest", "Outside", "2"));
        userRepository.save(new User( "stranger", "Outside", "3"));
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void editUser(String id, User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void addUser(String userName){
        if(userName.equals("parent")){
            userRepository.save(new User( "parent", "Outside", "0"));
        }
        else if(userName.equals("child")){
            userRepository.save(new User( "child", "Outside", "1"));
        }
        else if(userName.equals("guest")){
            userRepository.save(new User( "guest", "Outside", "2"));
        }
        else{
            userRepository.save(new User( "stranger", "Outside", "3"));
        }
    }

    public void changeUserLocation(Long id, String location){
        User changeLocation = userRepository.findById(id).orElse(null);
        changeLocation.setLocation(location);
        userRepository.save(changeLocation);
    }
}
