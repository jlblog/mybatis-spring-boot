package me.jlblog.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.jlblog.app.domain.Customer;
import me.jlblog.app.mapper.CustomerMapper;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerMapper customerMapper;
	
	public List<Customer> findAll(){
		return customerMapper.findAll();
	}
	
	public Customer findOne(Integer id){
		return customerMapper.findOne(id);
	}
	
	public Customer create(Customer customer){
		customerMapper.create(customer);
		return customer;
	}

	public Customer update(Customer customer){
		customerMapper.update(customer);
		return customer;
	}
	
	public void delete(Integer id){
		customerMapper.delete(id);
	}
	
}
