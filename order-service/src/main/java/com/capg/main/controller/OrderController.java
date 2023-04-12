package com.capg.main.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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

import com.capg.main.entity.Orders;
import com.capg.main.entity.Product;
import com.capg.main.service.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@CrossOrigin
@RequestMapping("/order")
@Component
@ComponentScan("com.capg.main")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@Autowired
	public RestTemplate restTemplate;
	
	
	@GetMapping("/getorder/{oid}")
	public ResponseEntity<Object> getOrderDetailsById(@PathVariable("oid") String oid)  {
			Orders pro= service.findById(oid);
			if(pro==null)
				return new ResponseEntity<Object>("Order Data not found",HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<Object>(pro,HttpStatus.OK);
		}
	
	@GetMapping("/getorders/{customerName}")
	public ResponseEntity<Object> getOrderDetailsById1(@PathVariable("customerName") String customerName)  {
			Orders pro= service.findByCustomerName(customerName);
			if(pro==null)
				return new ResponseEntity<Object>("Order Data not found",HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<Object>(pro,HttpStatus.OK);
		}
	
	@GetMapping("/getord/{date}")
	public ResponseEntity<Object> getOrderDetailsByDate(@PathVariable("date") String date)  {
			Orders pro= service.findByDate(date);
			if(pro==null)
				return new ResponseEntity<Object>("Order Data not found",HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<Object>(pro,HttpStatus.OK);
		}
	
	
	
	//ResponseEntity Configuration
	
	@GetMapping("/getall")
	public List<Orders> getOrder(){
		return service.getOrder();
	}
	
//	@GetMapping("/get/{id}")
//	public ResponseEntity<Object> getUserDetailsById(@PathVariable("id") String id)  {
//			Orders pro= service.findById(id);
//			if(pro==null)
//				return new ResponseEntity<Object>("Order Data not found",HttpStatus.EXPECTATION_FAILED);
//			return new ResponseEntity<Object>(pro,HttpStatus.OK);
//		}
	@PostMapping("/registerorder")
	public ResponseEntity<String> insertUser(@RequestBody Orders ord) {
		System.out.println("Order Done");
		service.addOrder(ord);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@PutMapping("/updateorder/{oid}")
	public ResponseEntity<Object> UpdateUserById1(@PathVariable("oid") String  oid, @RequestBody Orders ord) {
		Optional<Orders> op = service.findById1(oid);
		if (op.isPresent()) {
			Orders us = op.get();
			
			us.setCustomerName(ord.getCustomerName());
			us.setCustomerAddress(ord.getCustomerAddress());
			service.save(us);
			return new ResponseEntity<Object>(us, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Not updated successfully", HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/deleteorder/{oid}")
	public ResponseEntity<String> deleteproductById(@PathVariable("oid") String oid){
		Optional<Orders> op= service.findById1(oid);
		if(op.isPresent())
		{
			service.deleteById(oid);
		return new ResponseEntity<String>("Order is Deleted successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Order Not Found",HttpStatus.EXPECTATION_FAILED);
	}
	
}