package com.capg.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.main.entity.Orders;
import com.capg.main.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository crepo;
	
	public List<Orders> getOrder() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}

	public Orders addOrder(Orders ord) { 
		// TODO Auto-generated method stub
		return crepo.save(ord);
	}
	public void deleteById(String  id) {
		// TODO Auto-generated method stub
		crepo.deleteById(id);
	}
	public Optional<Orders> findById1(String  id) {
		// TODO Auto-generated method stub
		return crepo.findById(id);
	}
	public Orders updateOrder(Orders ord) { ///
		crepo.save(ord);
		return ord;
		
	}
//	public void deleteById23(int pid) {
//		// TODO Auto-generated method stub
//		crepo.deleteById(pid);
//	}

	public void save(Orders prod) {
		// TODO Auto-generated method stub
		crepo.save(prod);
	}

	public Orders findById(String id) {
		// TODO Auto-generated method stub
		Optional<Orders> op= crepo.findById(id);
		if(op.isPresent())
		{
			Orders odr= op.get();
			return odr;
		}
		return null;
		}

	public Orders findByDate(String date) {
		// TODO Auto-generated method stub
		Optional<Orders> op= crepo.findByDate(date);
		if(op.isPresent())
		{
			Orders odr= op.get();
			return odr;
		}
		
		return null;
	}

	public Orders findByCustomerName(String customerName) {
		// TODO Auto-generated method stub
		Optional<Orders> op= crepo.findByCustomerName(customerName);
		if(op.isPresent())
		{
			Orders odr1= op.get();
			return odr1;
		}
		return null;
	}

	
}