package me.jlblog.app.service;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Getter;
import me.jlblog.app.domain.User;

@Getter
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 스프링 시큐리티로 인증된 사용자 객체에서 User객체를 가져오기 위한 필드 
	 */
	private final User user;
	
	/**
	 * User 클래스의 생성자를 사용하여 '사용자이름', '암호', '허가 작업'을 할 수 있는 역할
	 * 역할 작성 시 AuthorityUtils를 사용
	 * @param user
	 */
	public LoginUserDetails(User user){
		super(user.getUsername(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}
}
