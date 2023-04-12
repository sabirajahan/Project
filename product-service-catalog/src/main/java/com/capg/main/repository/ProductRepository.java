package com.capg.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.main.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
