package soen343.backend;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import soen343.backend.user.User;
import soen343.backend.user.UserRepository;
import soen343.backend.user.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepo;
	
	@Test
	public void addUserTest() {
		User user = new User("John", "Garage", "0");
		userService.addUser(user);
		assertEquals(user, userRepo.findById((long) 1));
	}
	
	@Test
	public void addParentTest() {
		User user = new User("parent", "Outside", "0");
		userService.addParent();
		assertEquals(user, userRepo.findById((long) 1));
	}
	
	@Test
	public void addChildTest() {
		User user = new User("child", "Outside", "1");
		userService.addChildren();
		assertEquals(user, userRepo.findById((long) 1));
	}
	
	@Test
	public void addGuestTest() {
		User user = new User("guest", "Outside", "2");
		userService.addGuest();
		assertEquals(user, userRepo.findById((long) 1));
	}
	
	@Test
	public void addStrangerTest() {
		User user = new User("stranger", "Outside", "3");
		userService.addStranger();
		assertEquals(user, userRepo.findById((long) 1));
	}
	
	// Use case (4)
	@Test
	public void getUserTest() {
		long id = 1;
		User user = new User("parent", "Kitchen", "0");
		userRepo.save(user);	
		assertEquals(user, userRepo.findById(id));
	}
	
	// Use Case(3), (8), (9)
	@Test
	public void editUserTest() {
		User user = new User("parent", "Kitchen", "0");
		userRepo.save(user);
		user.setLocation("Outside");
		userRepo.save(user);
		assertEquals(user, userRepo.findById((long) 1));
	}
	
	
	
}