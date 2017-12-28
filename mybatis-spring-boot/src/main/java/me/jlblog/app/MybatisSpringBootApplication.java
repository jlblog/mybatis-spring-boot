package me.jlblog.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("me.jlblog.app.mapper")
@ComponentScan
public class MybatisSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisSpringBootApplication.class, args);
	}
}
