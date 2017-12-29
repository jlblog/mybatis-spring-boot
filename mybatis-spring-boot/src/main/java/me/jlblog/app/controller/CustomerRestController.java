package me.jlblog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	/**
	 * 신규 고객 정보 작성
	 * HTTP 메서드 POST 할당
	 * API 정상 동작시 201 CREATED 반환
	 * Customer 객체에 매핑을 위해 @RequestBody 애너테이션 설정
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer postCustomers(@RequestBody Customer customer){
		return customerService.create(customer);
	}
	
	// 고객 한 명의 정보 업데이트
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer){
		customer.setId(id);
		return customerService.update(customer);
	}
	
	
	/**
	 * 고객 한 명의 정보 삭제
	 * HttpStatus.NO_CONTENT 지정 시 204 No_CONTENT가 반환
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Integer id){
		customerService.delete(id);
	}
	
	
}
