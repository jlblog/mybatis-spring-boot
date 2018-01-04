package me.jlblog.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import me.jlblog.app.domain.User;

@Mapper
@Transactional
public interface UserMapper {

	User findOne(String username);
	
}
