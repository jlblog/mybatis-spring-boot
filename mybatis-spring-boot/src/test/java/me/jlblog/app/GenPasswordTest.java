package me.jlblog.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPasswordTest {

	public static void main(String[] args) {
		System.out.println("$2a$10$oSwQcJDUGS.VL6eoCMhj..A1kBbqTsRCLVw7MbNT.mldt40AyCJqC");
		System.out.printf(new BCryptPasswordEncoder().encode("demo"));

	}

}
