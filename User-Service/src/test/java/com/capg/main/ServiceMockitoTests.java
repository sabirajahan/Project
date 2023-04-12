package com.capg.main;

import org.springframework.boot.test.context.SpringBootTest;

import com.capg.main.models.User;
import com.capg.main.repository.UserRepository;
import com.capg.main.security.services.UserService;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;


@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {
@Mock         //Used for mocking the repository class
UserRepository urepo;

@InjectMocks  //Used for injecting/invoking the methods of service class
UserService service;

public List<User> myusers;

@Test
@Order(1)         //For executing this method first
public void test_getUsers()
{
	List<User> myusers=new ArrayList<User>();
	myusers.add(new User( "Roumita Paul", "roumita.das2000@gmail.com","Roumi@2000"));
	myusers.add(new User("Anindita Paul", "aninditapaul.das2000@gmail.com","AninditaP@2000"));
	when(urepo.findAll()).thenReturn(myusers);
	assertEquals(2,service.getUsers().size());        //Invoke the method from the service class
}

@Test
@Order(2)
public void test_addUser()
{
	User user = new User( "Rohan Biswas", "RohanBiswas.com","Rohan@2000");	
	when(urepo.save(user)).thenReturn(user);
	assertEquals(user,service.addUser(user));
}

@Test
@Order(3)
public void test_updateUser()
{
	User user = new User( "Rohan Biswas", "RohanBiswas.com","Rohan@2000");	
	when(urepo.save(user)).thenReturn(user);
	assertEquals(user,service.updateUser(user));
}


//@Test
//@Order(3)
//public void deleteById23() {
	
	//UserProfile user = new UserProfile(112, "Rohan Biswas", "RohanBiswas.com","Male", "Malda","Rohan@2000");
	//when(crepo.deleteById23().thenReturn(user));
	//assertEquals(user,service.deleteById23(1));
	
//@Test
//@Order(4)
//public void test_deleteUser() {
//	User user = new User( "Rohan Biswas", "RohanBiswas.com","Rohan@2000");	
//	service.deleteByName12("Rohan Biswas");
//	verify(urepo, times(1)).deleteByName(user.getUsername());
//}
	
	
	
	
	
	
	
	
	
	
	
	

}