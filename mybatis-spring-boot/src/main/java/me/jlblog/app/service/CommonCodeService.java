package me.jlblog.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.jlblog.app.domain.CommonCode;
import me.jlblog.app.mapper.CommonCodeMapper;

@Service
public class CommonCodeService {
	
	@Autowired
	CommonCodeMapper commonCodeMapper;

	public List<CommonCode> findAll() {
		return commonCodeMapper.findAll();
	}

	public CommonCode findOne(String code) {
		return commonCodeMapper.findOne(code);
	}
	
	public int create(CommonCode param){
		return commonCodeMapper.create(param);
	}

	public int update(CommonCode param){
		return commonCodeMapper.update(param);
	}
	
	public void delete(CommonCode param){
		commonCodeMapper.delete(param);
	}
	
}
