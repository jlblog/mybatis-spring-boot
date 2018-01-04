package me.jlblog.app.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customers")
public class User {
	
	private String username;
	
	@JsonIgnore
	private String encodedPassword;
	
	@JsonIgnore
	private List<Customer> customers;
}
