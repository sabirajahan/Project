package com.capg.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.main.entity.Product;
import com.capg.main.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository crepo;
	
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}

	public Product addProduct(Product product) { ///
		// TODO Auto-generated method stub
		return crepo.save(product);
	}
	public void deleteById(String  id) {
		// TODO Auto-generated method stub
		crepo.deleteById(id);
	}
	public Optional<Product> findById1(String  id) {
		// TODO Auto-generated method stub
		return crepo.findById(id);
	}
	public Product updateProduct(Product product) { ///
		crepo.save(product);
		return product;
		
	}
//	public void deleteById23(int pid) {
//		// TODO Auto-generated method stub
//		crepo.deleteById(pid);
//	}

	public void save(Product prod) {
		// TODO Auto-generated method stub
		crepo.save(prod);
	}

	public Product findById(String id) {
		// TODO Auto-generated method stub
		Optional<Product> op= crepo.findById(id);
		if(op.isPresent())
		{
			Product pro= op.get();
			return pro;
		}
		return null;
		}

	
}