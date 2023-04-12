package com.capg.main.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.capg.main.entity.Orders;

public interface OrderRepository extends MongoRepository<Orders, String>{

	Optional<Orders> findByDate(String oDate);

	Optional<Orders> findByCustomerName(String customerName);
	
}
