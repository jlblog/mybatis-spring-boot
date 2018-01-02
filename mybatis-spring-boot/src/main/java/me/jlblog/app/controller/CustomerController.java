package me.jlblog.app.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import me.jlblog.app.domain.Customer;
import me.jlblog.app.domain.CustomerForm;
import me.jlblog.app.service.CustomerService;

@Controller
@RequestMapping("customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@ModelAttribute
	CustomerForm setUpForm(){
		return new CustomerForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Validated CustomerForm form, BindingResult result, Model model){
		if(result.hasErrors()){
			return list(model);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form,  customer);
		System.out.println("customer: " + customer.toString());
		customerService.create(customer);
		return "redirect:/customers";
	}
	
	/**
	 * 편집화면으로 이동
	 * @param id
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/edit", params = "form", method = RequestMethod.GET)
	public String editForm(@RequestParam Integer id, CustomerForm form){
		Customer customer = customerService.findOne(id);
		BeanUtils.copyProperties(customer, form);	// customer정보를 form에 복사
		return "customers/edit";
	}
	
	/**
	 * 편집 저장
	 * @param id
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result){
		if(result.hasErrors()){
			return editForm(id, form);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customer.setId(id);
		customerService.update(customer);
		
		return "redirect:/customers";
	}
	
	/**
	 * 목록으로 돌아가기
	 * @return
	 */
	@RequestMapping(value = "/edit", params = "goToTop")
	public String goToTop(){
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String edit(@RequestParam Integer id){
		customerService.delete(id);
		return "redirect:/customers";
	}
	
}
