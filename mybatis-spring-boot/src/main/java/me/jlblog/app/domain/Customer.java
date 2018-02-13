package me.jlblog.app.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private Date regDate;
	private User user;
}
