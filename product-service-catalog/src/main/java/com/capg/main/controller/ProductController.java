package com.capg.main.controller;

import java.util.Arrays;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.main.entity.Product;

import com.capg.main.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Component
@ComponentScan("com.capg.main")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@Autowired
	public RestTemplate restTemplate;
	
	//RestTemplate Configuration
	
//	@GetMapping("/getuser")
//	public List<Object> getUser() {
//		Object[] objects = restTemplate.getForObject("http://user-service/api/test/getall", Object[].class);
//		return Arrays.asList(objects);
//	}
	
	//ResponseEntity Configuration
	
	@GetMapping("/getall")
	public List<Product> getProduct(){
		return service.getProducts();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getUserDetailsById(@PathVariable("id") String id)  {
			Product pro= service.findById(id);
			if(pro==null)
				return new ResponseEntity<Object>("User Data not found",HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<Object>(pro,HttpStatus.OK);
		}
	@PostMapping("/register")
	public ResponseEntity<String> insertUser(@RequestBody Product user) {
		System.out.println("Product registered");
		service.addProduct(user);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> UpdateUserById1(@PathVariable("id") String  id, @RequestBody Product cum) {
		Optional<Product> op = service.findById1(id);
		if (op.isPresent()) {
			Product us = op.get();
			
			
			us.setPname(cum.getPname());
			us.setPrice(cum.getPrice());
			us.setCategory(cum.getCategory());
			us.setDesc(cum.getDesc());
			us.setImage(cum.getImage());
			service.save(us);
			return new ResponseEntity<Object>(us, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/deleteproduct/{id}")
	public ResponseEntity<String> deleteproductById(@PathVariable("id") String id){
		Optional<Product> op= service.findById1(id);
		if(op.isPresent())
		{
			service.deleteById(id);
		return new ResponseEntity<String>("User Deleted successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("User Not Found",HttpStatus.EXPECTATION_FAILED);
	}
	
}