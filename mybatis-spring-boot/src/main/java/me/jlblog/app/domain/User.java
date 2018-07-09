package me.jlblog.app.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString(exclude = "customers")
public class User {
	
	private String username;

	@JsonIgnore
	private String encodedPassword;

	@JsonIgnore
	private List<Customer> customers;

	public User (String username, String password) {
		this.username = username;
		this.encodedPassword = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
