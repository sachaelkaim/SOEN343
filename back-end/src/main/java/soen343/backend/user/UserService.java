package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void addBaseUsers(){
        userRepository.save(new User( "parent", "outside", "0"));
        userRepository.save(new User( "child", "outside", "1"));
        userRepository.save(new User( "guest", "outside", "2"));
        userRepository.save(new User( "stranger", "outside", "3"));
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

    public void addParent(){
        userRepository.save(new User( "parent", "outside", "0"));
    }

    public void addChildren(){
        userRepository.save(new User( "child", "outside", "1"));
    }


    public void addGuest(){
        userRepository.save(new User( "guest", "outside", "2"));
    }

    public void addStranger(){
        userRepository.save(new User( "stranger", "outside", "3"));
    }

    public void editUser(String id, User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
