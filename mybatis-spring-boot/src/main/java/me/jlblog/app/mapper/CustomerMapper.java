package me.jlblog.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import me.jlblog.app.domain.Customer;

@Mapper
public interface CustomerMapper {
	public List<Customer> findAll();
	public Customer findOne(Integer id);
	public Customer create(Customer customer);
	public Customer update(Customer customer);
	public void delete(Integer id);
}
