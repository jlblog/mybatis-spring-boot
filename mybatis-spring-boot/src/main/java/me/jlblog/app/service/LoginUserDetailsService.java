package me.jlblog.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.jlblog.app.domain.User;
import me.jlblog.app.mapper.UserMapper;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userMapper.findOne(username);
		if(user == null){
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		
		return new LoginUserDetails(user);
	}
}
