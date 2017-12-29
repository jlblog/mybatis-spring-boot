package me.jlblog.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import me.jlblog.app.domain.Customer;

@Mapper
@Transactional
public interface CustomerMapper {
	List<Customer> findAll();
	Customer findOne(Integer id);
	void create(Customer customer);
	void update(Customer customer);
	void delete(Integer id);
}
