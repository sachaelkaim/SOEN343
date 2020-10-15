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
        userRepository.save(new User("1", "parent", "outside", "0"));
        userRepository.save(new User("2", "child", "outside", "1"));
        userRepository.save(new User("3", "guest", "outside", "2"));
        userRepository.save(new User("4", "stranger", "outside", "3"));
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id){
        return userRepository.findById(id);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    // if the header is .. addparent


    public void editUser(String id, User user){
        userRepository.save(user);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
