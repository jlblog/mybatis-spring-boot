package me.jlblog.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** 
	 * 특정 요청에 대해서는 시큐리티 설정을 무시하도록 설정
	 */
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	/** 
	 * 인가, 로그인, 로그아웃 설정
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/loginForm").permitAll()
			.anyRequest().authenticated();
		http.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/loginForm")
			.failureUrl("/loginForm?error")
			.defaultSuccessUrl("/dashboard", true)
			.usernameParameter("username").passwordParameter("password")
			.and();
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
			.logoutSuccessUrl("/loginForm");
	}
	
	@Configuration
	static class AutheticationConfiguration extends GlobalAuthenticationConfigurerAdapter{
		
		@Autowired
		UserDetailsService userDetailsService;
		
		/**
		 * 해시 형식으로 암호화
		 * BCrypt 해싱 알고리즘 선택
		 */
		@Bean
		PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
		
		/** 
		 * 인증처리 관련 설정
		 * UserDetailsService와 암호를 대조 
		 */
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());				
		}
	}
	
}
