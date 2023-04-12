package com.capg.main.security.services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.capg.main.entity.Product;
import com.capg.main.models.User;
import com.capg.main.repository.UserRepository;


@Service

public class UserService {
	@Autowired
	private UserRepository urepo;
	
	public User findById(String id)  {
		// TODO Auto-generated method stub
		Optional<User> op= urepo.findById(id);
		if(op.isPresent())
		{
			User user= op.get();
			return user;
		}
		return null;
		}
	
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return urepo.findAll();
	}
	public Optional<User> findById1(String id) {
		// TODO Auto-generated method stub
		return urepo.findById(id);
	}
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		urepo.deleteById(id);
	}
	public void deleteById23(String id) {
		// TODO Auto-generated method stub
		urepo.deleteById(id);
	}
	public User  addUser(User user) { ///
		// TODO Auto-generated method stub
		return urepo.save(user);
	}
	public void save(User us) {
		// TODO Auto-generated method stub
		urepo.save(us);
	}
	public User updateUser(User user) { ///
		urepo.save(user);
		return user;
		
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Optional<User> op= urepo.findByUsername(username);
				if(op.isPresent())
				{
					User user= op.get();
					return user;
				}
				return null;
				}
//	public void deleteByName12(String username) {
//		urepo.deleteByName(username);
		// TODO Auto-generated method stub
		
//	}

}

