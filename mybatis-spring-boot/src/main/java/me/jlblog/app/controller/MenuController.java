package me.jlblog.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {
	
	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String callDashboard() {
		return "/menu/dashboard";
	}

	@RequestMapping(value = "common-code", method = RequestMethod.GET)
	public String callCommonCode() {
		return "/menu/commonCode";
	}
}
