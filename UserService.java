package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Function that gives the Default user
     */
    @Bean
    public void addBaseUsers(){
        userRepository.save(new User( "parent", "Outside", "0"));
        userRepository.save(new User( "child", "Outside", "1"));
        userRepository.save(new User( "guest", "Outside", "2"));
        userRepository.save(new User( "stranger", "Outside", "3"));
    }
    
    /**
     * Function that gives a list of all Users
     */
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Function that retrieve the user using it's Id
     * @param id
     * @return
     */
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Function to add a new User 
     * @param user
     */
    public void addUser(User user){
        userRepository.save(user);
    }

    /**
     * Function to add a parent
     */
    public void addParent(){
        userRepository.save(new User( "parent", "Outside", "0"));
    }

    /**
     * Function to add a children
     */
    public void addChildren(){
        userRepository.save(new User( "child", "Outside", "1"));
    }

    /**
     * Function to add a guest
     */
    public void addGuest(){
        userRepository.save(new User( "guest", "Outside", "2"));
    }

    /**
     * Function to add a stranger
     */
    public void addStranger(){
        userRepository.save(new User( "stranger", "Outside", "3"));
    }
   
    /**
     * Function to change the details of the User
     * @param id
     * @param user
     */
    public void editUser(String id, User user){
        userRepository.save(user);
    }

    /**
     * Function to remover a user
     * @param id
     */
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
