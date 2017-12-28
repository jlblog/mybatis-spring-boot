package me.jlblog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.jlblog.app.domain.Customer;
import me.jlblog.app.service.CustomerService;

@RestController
@RequestMapping("api/custommers")
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Customer> getCustomers(){
		List<Customer> customers = customerService.findAll();
		return customers;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable Integer id){
		Customer customer = customerService.findOne(id);
		return customer;
	}
	
}
