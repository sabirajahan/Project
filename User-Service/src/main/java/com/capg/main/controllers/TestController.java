package com.capg.main.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.main.repository.UserRepository;
import com.capg.main.security.services.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.main.models.Orders;
import com.capg.main.models.Product;
import com.capg.main.models.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository urepo;

	@Autowired
	public RestTemplate restTemplate;
	
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
//ResponseEntity Configuration
//--------------------------GET ALL USER DATA (ALL)-----------------------------------------------------------------------------------	
	
	@GetMapping("/getall")
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	public List<User> getUsers(){
		return service.getUsers();
	}
	
//---------------------  GET THE USER-LIST BY USERID (ADMIN & MOD)--------------------------------------------------------------------	
	
	@GetMapping("/get/id/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<Object> getUserDetailsById(@PathVariable("id") String id)  {
			User us = service.findById(id);
			if(us==null)
				return new ResponseEntity<Object>("User Data not found",HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<Object>(us,HttpStatus.OK);
		}

//----------------------GET THE USER-LIST BY USERNAME (ADMIN & MOD)---------------------------------------------------------------	
	
	@GetMapping("/get/username/{username}")
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
		public ResponseEntity<Object> getUserDetailsByUserName(@PathVariable("username") String username)  {
			User us = service. findByUsername(username);
			if(us==null)
				return new ResponseEntity<Object>("User Data not found",HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<Object>(us,HttpStatus.OK);
		}
	
	
	
	
//RestTemplate Configuration
//------------------------Getting the PRODUCT-LIST (ALL)-----------------------------------------------------------------------
	@GetMapping("/getproduct")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Object> getProduct() {
		Object[] objects = restTemplate.getForObject("http://product-service-catalog/product/getall", Object[].class);
		return Arrays.asList(objects);
	}

//-------------------------Get product details by product id--------------------------------------------------------------	
	
	
	@RequestMapping(value = "/getproduct/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	   public String getAllProducts(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      return restTemplate.exchange(
	         "http://product-service-catalog/product/get/"+id, HttpMethod.GET, entity, String.class).getBody();
	   }
	
//-------------------------REGISTER THE PRODUCT (ADMIN & MOD)----------------------------------------------------------------------	
		
	@RequestMapping(value = "/register/products", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	   public String createProducts(@RequestBody Product product) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	      
	      return restTemplate.exchange(
	         "http://product-service-catalog/product/register", HttpMethod.POST, entity, String.class).getBody();
	   }
//--------------------------UPDATE THE PRODUCT(ADMIN & MOD)---------------------------------------------------------------------------	
		
	@RequestMapping(value = "/update/products/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	  public String updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	      
	      return restTemplate.exchange(
	         "http://product-service-catalog/product/update/"+id, HttpMethod.PUT, entity, String.class).getBody();
	   }
//--------------------------DELETE THE PRODUCT(ADMIN)----------------------------------------------------------------------------------------	
	
	@RequestMapping(value = "/delete/products/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	   public String deleteProduct(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      
	      return restTemplate.exchange(
	         "http://product-service-catalog/product/deleteproduct/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }
	
/////////FOR ORDER -SERVICE.........
	
//--------------------------Getting the ORDER-LIST(ADMIN + MODERATOR)----------------------------------------------------------------------------------------
	
	@GetMapping("/getorder")
	@PreAuthorize(" hasAnyRole('MODERATOR','ADMIN')")
	public List<Object> getOrders() {
		Object[] objects = restTemplate.getForObject("http://order-service/order/getall", Object[].class);
		return Arrays.asList(objects);
	}
	
//--------------------Getting the ORDER-LIST By ORDER-ID (ADMIN + MODERATOR + USER)----------------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "/getorders/{oid}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	   public String getAllOrders(@PathVariable("oid") String oid) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Orders> entity = new HttpEntity<Orders>(headers);
	      
	      return restTemplate.exchange(
	         "http://order-service/order/getorder/"+oid, HttpMethod.GET, entity, String.class).getBody();
	   }
	
//-----------------Getting the ORDER-LIST By ORDER-DATE (ADMIN + MODERATOR + USER)----------------------------------------------------------------------------------------
		

	@RequestMapping(value = "/getorder/{date}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	   public String getAllOrder(@PathVariable("date") String date) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Orders> entity = new HttpEntity<Orders>(headers);
	      
	      return restTemplate.exchange(
	         "http://order-service/order/getord/"+date, HttpMethod.GET, entity, String.class).getBody();
	   }
	
	
//----------------Getting the ORDER-LIST By CUSTOMER_NAME (ADMIN + MODERATOR + USER)----------------------------------------------------------------------------------------
		
	@RequestMapping(value = "/getord/{customerName}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	   public String getAllOrd(@PathVariable("customerName") String customerName) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Orders> entity = new HttpEntity<Orders>(headers);
	      
	      return restTemplate.exchange(
	         "http://order-service/order/getorders/"+customerName, HttpMethod.GET, entity, String.class).getBody();
	   }
//--------------------------------PLACE THR ORDER(USER)----------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/place/order", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	   public String createOrders(@RequestBody Orders order) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Orders> entity = new HttpEntity<Orders>(order,headers);
	      
	      return restTemplate.exchange(
	         "http://order-service/order/registerorder", HttpMethod.POST, entity, String.class).getBody();
	   }
//-------------------------------UPDATE THR ORDER(USER)------------------------------------------------------------------------------------	
	
	@RequestMapping(value = "/update/order/{oid}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('USER')")
	  public String updateOrder(@PathVariable("oid") String oid, @RequestBody Orders order) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Orders> entity = new HttpEntity<Orders>(order,headers);
	      
	      return restTemplate.exchange(
	         "http://order-service/order/updateorder/"+oid, HttpMethod.PUT, entity, String.class).getBody();
	
	}
//------------------------------DELETE THR ORDER(USER + ADMIN)----------------------------------------------------------------------------	
	@RequestMapping(value = "/delete/order/{oid}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	   public String deleteOrder(@PathVariable("oid") String oid) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Orders> entity = new HttpEntity<Orders>(headers);
	      
	      return restTemplate.exchange(
	         "http://order-service/order/deleteorder/"+oid, HttpMethod.DELETE, entity, String.class).getBody();
	   }

//-------------------------------DELETE USER BY USER ID (ADMIN & USER)-------------------------------------------------------------------	
	
	
	@DeleteMapping("/deleteuser/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<String> deletecustomerById(@PathVariable("id") String id){
		Optional<User> op= service.findById1(id);
		if(op.isPresent())
		{
			service.deleteById(id);
		return new ResponseEntity<String>("User Deleted successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("User Not Found",HttpStatus.EXPECTATION_FAILED);
	}
//------------------------------------REGISTER FOR USER(USER)----------------------------------------------------------------------------------------

        @PostMapping("/register")
		@PreAuthorize("hasRole('USER')")
		public ResponseEntity<String> insertUser(@RequestBody User user) {    //RequestBody maps the HttpRequest body to a domain object
			System.out.println("User registered");
			service.addUser(user);
			return new ResponseEntity<String>("Success", HttpStatus.OK);     //ResponseEntity represents the HTTP response
		}
        
//---------------------------------------UPDATE FOR USER-----------------------------------------------------------------------------------------
       
        @PutMapping("/update/{id}")
        @PreAuthorize("hasRole('USER')")
    	public ResponseEntity<Object> UpdateUserById1(@PathVariable("id") String id, @RequestBody User cum) {
    		Optional<User> op = service.findById1(id);
    		if (op.isPresent()) {
    			User us = op.get();
    			
    			us.setEmail(cum.getEmail());
    			us.setUsername(cum.getUsername());
    			us.setPassword(cum.getPassword());
    			service.save(us);
    			return new ResponseEntity<Object>(us, HttpStatus.OK);
    		} else {
    			return new ResponseEntity<Object>("Not updated successfully", HttpStatus.NOT_FOUND);
    		}
    	}
}