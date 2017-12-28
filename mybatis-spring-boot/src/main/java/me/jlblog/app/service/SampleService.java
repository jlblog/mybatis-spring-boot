package me.jlblog.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.jlblog.app.domain.Samples;
import me.jlblog.app.mapper.SampleMapper;

@Service
public class SampleService {

	@Autowired
	public SampleMapper sampleMapper;
	
	public List<Samples> findAllSample() throws Exception {
		return sampleMapper.findAllSample();
	}
}
