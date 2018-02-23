package me.jlblog.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import me.jlblog.app.domain.CommonCode;

@Mapper
public interface CommonCodeMapper {
	List<CommonCode> findAll();
	CommonCode findOne(String code);
	int create(CommonCode param);
	int update(CommonCode param);
	void delete(CommonCode param);
}
