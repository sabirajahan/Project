package com.capg.main.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.main.models.ERole;
import com.capg.main.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERole name);
	}