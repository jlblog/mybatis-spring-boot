package me.jlblog.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("me.jlblog.app.mapper")
@ComponentScan
public class MybatisSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisSpringBootApplication.class, args);
	}
	
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Bean
	CharacterEncodingFilter characterEncodingFilter(){
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}
}
