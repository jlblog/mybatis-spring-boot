package me.jlblog.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import me.jlblog.app.domain.Customer;
import me.jlblog.app.service.CustomerService;
import me.jlblog.app.service.LoginUserDetails;

@RestController
@RequestMapping("api/customers")
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
	 * UriComponentsBuilder : 컨텍스트 상대로 URI를 쉽게 만들어 줌
	 */
	//@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Customer> postCustomers(@RequestBody Customer customer,
			UriComponentsBuilder uriBuilder,@AuthenticationPrincipal LoginUserDetails userDetails){
		Customer created = customerService.create(customer, userDetails.getUser());
		URI location = uriBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}
	
	// 고객 한 명의 정보 업데이트
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer,
			@AuthenticationPrincipal LoginUserDetails userDetails){
		customer.setId(id);
		return customerService.update(customer, userDetails.getUser());
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
