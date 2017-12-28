package me.jlblog.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private Date regDate;
}
