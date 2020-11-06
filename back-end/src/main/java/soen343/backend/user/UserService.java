package soen343.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import soen343.backend.CoreModuleController;
import soen343.backend.CoreModuleModel;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import java.util.Iterator;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    private ObjectMapper mapper;
    private File usersFile;

    @Autowired
    private ConsoleService notifications;

    @Bean
    public void addBaseUsers(){
    	mapper = new ObjectMapper();
    	User parent = new User( "parent", "Outside", "0");
    	User child = new User( "child", "Outside", "1");
    	User guest = new User( "guest", "Outside", "2");
    	User stranger = new User( "stranger", "Outside", "3");
    	
    	
        /*userRepository.save(parent);
        userRepository.save(child);
        userRepository.save(guest);
        userRepository.save(stranger);*/
    	try 
    	{
    		List<User> initUsers = Arrays.asList(parent,child,guest,stranger);
    		usersFile = new File("./src/main/resources/json/users.json");
    		/*String parentInfo = mapper.writeValueAsString(parent);
    		String childInfo = mapper.writeValueAsString(child);*/
    		List<String> lines = Files.readAllLines(usersFile.toPath());
    		if (lines.size() == 0 ) {
    			//Files.write(usersFile.toPath(), Arrays.asList(parentInfo), StandardOpenOption.CREATE);
    			mapper.writeValue(usersFile, initUsers);
    		} else {
    			//Files.write(usersFile.toPath(), Arrays.asList(parentInfo), StandardOpenOption.APPEND);
    		}
    		
    		//Files.write(usersFile.toPath(), Arrays.asList(childInfo), StandardOpenOption.APPEND);
    		
    		
    		
    		/*FileWriter fileWriter = new FileWriter(usersFile, true);
    		
    		SequenceWriter seqWriter = mapper.writer().writeValuesAsArray(fileWriter);
    		seqWriter.write(parent);
    		seqWriter.write(child);
    		seqWriter.write(guest);
    		seqWriter.write(stranger);
    		seqWriter.close();*/
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
        try 
    	{
    		usersFile = new File("./src/main/resources/json/users.json");
    		Iterable<User> previousUsersIter = userRepository.findAll();
    		List<User> updatedUsers = StreamSupport
    				  .stream(previousUsersIter.spliterator(), false)
    				  .collect(Collectors.toList());
    		updatedUsers.add(user);
    		mapper.writeValue(usersFile, updatedUsers);
    	} catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void addParent(){
        addUser(new User( "new parent", "Outside", "0"));
    }

    public void addChildren(){
        addUser(new User( "new child", "Outside", "1"));
    }

    public void addGuest(){
        addUser(new User( "new guest", "Outside", "2"));
    }

    public void addStranger(){
        addUser(new User( "stranger", "Outside", "3"));
    }
    
    public void editUser(String id, User user){
    	Iterable<User> previousUsersIter = userRepository.findAll();
		List<User> previousUsers = StreamSupport
				  .stream(previousUsersIter.spliterator(), false)
				  .collect(Collectors.toList());
		for (User prevUser: previousUsers) {
			if (prevUser.getId() == user.getId()) {
				prevUser.setName(user.getName());
				prevUser.setLocation(user.getLocation());
				prevUser.setPrivilege(user.getPrivilege());
				break;
			}
		}
		
		save(previousUsers);
		
		try 
    	{
    		usersFile = new File("./src/main/resources/json/users.json");
    		mapper.writeValue(usersFile, previousUsers);
    	} catch (IOException e) {
            e.printStackTrace();
        }		
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

    public User login(Long id){
        User loggedUser = userRepository.findById(id).orElse(null);
        notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHS","Logged in User: ID " + loggedUser.getId() + " " + loggedUser.getName() + "."));
        return loggedUser;
    }

    public boolean changeUserLocation(Long id, String location){
        if(location.equals("Select location")){
            return false;
        }
        else{
            User changeLocation = userRepository.findById(id).orElse(null);
            changeLocation.setLocation(location);
            userRepository.save(changeLocation);
            notifications.saveNotification(new Console(CoreModuleModel.getTime(),"SHS","ID: " + changeLocation.getId() + " has moved to the " + changeLocation.getLocation() + "."));
            return true;
        }
    }

    public boolean allUsersOutside(){
        Iterable<User> users = userRepository.findAll();
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()){
            User user = iter.next();
            if(!"Outside".equals(user.getLocation())){
                return false;
            }
        }
        return true;
    }
    
    public void save(List<User> users) {
    	userRepository.saveAll(users);
    }

}
