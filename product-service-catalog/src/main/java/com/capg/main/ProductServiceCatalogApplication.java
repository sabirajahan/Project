package com.capg.main;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan("com.capg.main") //to scan packages mentioned
@EnableMongoRepositories("com.capg.main") //to activate MongoDB repositories
//@EnableMongoRepositories(basePackageClasses = ProfileRepository.class)
@EnableEurekaClient
public class ProductServiceCatalogApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceCatalogApplication.class, args);
	}

}
