package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    private ObjectMapper mapper;

    @Bean
    public void addBaseUsers(){
    	mapper = new ObjectMapper();
    	User parent = new User( "parent", "Outside", "0");
    	User child = new User( "child", "Outside", "1");
    	User guest = new User( "guest", "Outside", "2");
    	User stranger = new User( "stranger", "Outside", "3");
    	
        userRepository.save(parent);
        userRepository.save(child);
        userRepository.save(guest);
        userRepository.save(stranger);
    	try 
    	{
    		File usersFile = new File("./src/main/resources/json/users.json");
    		 mapper.writeValue(usersFile,parent);
    		 mapper.writeValue(usersFile,child);
    		 mapper.writeValue(usersFile,guest);
    		 mapper.writeValue(usersFile,stranger);
    	} catch (IOException e) {
            e.printStackTrace();
        }
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
        userRepository.save(new User( "parent", "Outside", "0"));
    }

    public void addChildren(){
        userRepository.save(new User( "child", "Outside", "1"));
    }

    public void addGuest(){
        userRepository.save(new User( "guest", "Outside", "2"));
    }

    public void addStranger(){
        userRepository.save(new User( "stranger", "Outside", "3"));
    }

    public void editUser(String id, User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    
    public void save(List<User> users) {
    	userRepository.saveAll(users);
    }

}
