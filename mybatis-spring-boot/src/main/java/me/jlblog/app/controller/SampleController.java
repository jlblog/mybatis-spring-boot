package me.jlblog.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.jlblog.app.domain.Samples;
import me.jlblog.app.service.SampleService;

@RestController
@RequestMapping("/rest/samples")
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	public SampleService sampleService;
	
	@GetMapping("/all")
	public String getSampleAll() throws Exception {
		logger.info("called all");
		
		List<Samples> sampleList = sampleService.findAllSample();
		
		sampleList.stream().forEach(obj -> System.out.println(obj.getId() + obj.getName()));
		
		return null; 
	}
	
}
